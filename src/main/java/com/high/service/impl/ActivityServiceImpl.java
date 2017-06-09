package com.high.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.high.entity.*;
import com.high.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.high.mapper.ActivityMapper;
import com.high.utils.TimeUtils;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private LocationService locationService; 
	@Autowired
	private HttpSolrServer server;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ParticipateService participateService;
//	private HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr");
	@Override
	public void createActivity(Activity activity) {
		// TODO 实现创建 活动的 功能

		//插入活动地址，并获得地址id
		locationService.insertLocation(activity.getActivityLocation());
		activity.setActivityLocationId(activity.getActivityLocation().getLocationId());
		//是否有距离限制，若有则获得用户当前位置，并插入数据库，且获得id
		if(activity.getDistance() != null && activity.getDistance() >0){
			locationService.insertLocation(activity.getCreator().getLocation());
			activity.setCreatorLocationId(activity.getCreator().getLocationId());
		}
		//将活动添加到数据库，并获得id，返回
		activity.setActivityId(UUID.randomUUID().toString());
		activityMapper.insertActivity(activity);
		updateActivityIndexInSolr(activity);
	}

    /**
     * 添加活动时 更新索引
     * @param activity
     */
    private void updateActivityIndexInSolr(Activity activity) {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id",activity.getActivityId());
        doc.addField("activity_content",activity.getContent());
        doc.addField("activity_comment",activity.getComment());
        doc.addField("activity_top_category",activity.getCategory().getTopCategory());
        doc.addField("activity_secondary_category",activity.getCategory().getSecondaryCategory());
        doc.addField("activity_categoty_id",activity.getCategotyId());
        doc.addField("activity_start_time",TimeUtils.formatTimeForSolr(activity.getStartTime()));
        doc.addField("activity_location_description",activity.getActivityLocation().getLocationDescription());
        doc.addField("activity_location_id",activity.getActivityLocationId());
        doc.addField("activity_location_position",activity.getActivityLocation().getLongitude()+" " +activity.getActivityLocation().getLatitude());

        try {
            server.add(doc);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
	public SearchActivityResultModel searchActivity(SearchActivityQueryModel queryModel) {
		SolrQuery query = new SolrQuery();
		//如果输入了搜索条件，则搜索，否则搜索全部
		if(StringUtils.isNotEmpty(queryModel.getQuery())){
			query.setQuery(queryModel.getQuery());
		}else{
			query.setQuery("*:*");
		}
		if(StringUtils.isNotEmpty(queryModel.getTopCategory())){
			query.addFilterQuery("activity_top_category:"+queryModel.getTopCategory());
		}
		if(StringUtils.isNotEmpty(queryModel.getSecCategory())){
			query.addFilterQuery("activity_secondary_category:"+queryModel.getSecCategory());
		}
        Location location = queryModel.getActivityLocation();
		setLocationQuery(location,query,"10");

		if(queryModel.getStartTime() != null){
			// TODO 这块的逻辑是什么？？？过滤时输入的是什么 时间？？？又需要查询些什么？？？
			// 并将本地时间改为 MTS时间
			query.addFilterQuery("activity_start_time: ["+TimeUtils.formatTimeForSolr(queryModel.getStartTime())+" TO *]" );
			
		}
		if(queryModel.getPage() ==null){
			queryModel.setPage(1);
		}
		query.setStart((queryModel.getPage()-1)*SearchActivityQueryModel.NUM_ACTIVITY_PER_PAGE);
		query.setRows(SearchActivityQueryModel.NUM_ACTIVITY_PER_PAGE);
		//设置默认域
		query.set("df", "activity_keyword");
		//设置高亮
		query.setHighlight(true);
		query.setHighlightSimplePre("<font style='color:red'>");
		query.setHighlightSimplePost("</font>");
		
		try {
			return query(query, queryModel.getPage());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

    private void setLocationQuery(Location location, SolrQuery query, String d) {
        if(location !=null ){
            // TODO 根据位置进行索引
            query.set("d",d);
            query.set("fq", "{!geofilt}");//距离过滤函数
            query.set("pt", location.getLongitude()+" "+location.getLatitude());
            query.set("sfield", "activity_location_position");
        }
    }

    @Override
	public List<Activity> searchAllActivity() {
		return activityMapper.searchAllActivity();
	}

	@Override
	public List<Activity> searchActivityFromNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchActivityResultModel defaultSearchActivity(Location location) {
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
        setLocationQuery(location,query,"10");
		Date date = new Date();
		query.addFilterQuery("activity_start_time: ["+TimeUtils.formatTimeForSolr(date)+" TO *]" );
		query.setStart(0);
		query.setRows(SearchActivityQueryModel.NUM_ACTIVITY_PER_PAGE);
		//设置默认域
		query.set("df", "activity_keyword");
		try {
			return query(query, 1);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Activity findActivityById(String id) {
	    //通过活动id查找活动
		Activity activity = activityMapper.findActivityById(id);
        //获得相应的位置信息
		Location activityLocation = locationService.findLocationById(activity.getActivityLocationId());
		activity.setActivityLocation(activityLocation);
        //活动创建者信息
        User creator = userService.findUserById(activity.getCreatorId());
        Location creatorLocation = locationService.findLocationById(creator.getLocationId());
		creator.setLocation(creatorLocation);
		activity.setCreator(creator);
        //获得分类信息
		Category category = categoryService.findCategotyById(activity.getCategotyId());
		activity.setCategory(category);
        //获得参与者信息
        List<User> participates = participateService.getParticipatesByActivityId(id);
        activity.setParticipate(participates);
        return activity;
	}

    @Override
    public void deleteActivityById(String id) {
        activityMapper.deleteActivityById(id);
        deleteActivityIndexInSolr(id);
    }

    /**
     * 删除索引
     * @param id
     */
    private void deleteActivityIndexInSolr(String id) {
        try {
            server.deleteById(id);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SearchActivityResultModel query(SolrQuery query,Integer thisPage) throws SolrServerException{
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
		long count = results.getNumFound();
		List<Activity> activitys = new LinkedList<Activity>();
		Activity activity = null;
		for(SolrDocument doc : results){
			activity = new Activity();
			
			activity.setActivityId(doc.get("id").toString());
			activity.setComment(doc.get("activity_comment").toString());
			activity.setContent(doc.get("activity_content").toString());
			
			activity.setStartTime((Date)doc.get("activity_start_time"));
			activitys.add(activity);
		}
		SearchActivityResultModel resultModel = new SearchActivityResultModel();
		resultModel.setActivityList(activitys);
		resultModel.setCurPage(thisPage);
		resultModel.setRecordCount(count);
		resultModel.setPageCount((int) ((count+ SearchActivityQueryModel.NUM_ACTIVITY_PER_PAGE-1)/SearchActivityQueryModel.NUM_ACTIVITY_PER_PAGE));
		return resultModel;
	}
}