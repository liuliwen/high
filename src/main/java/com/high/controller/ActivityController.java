package com.high.controller;

import com.high.entity.*;
import com.high.service.ParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.high.service.ActivityService;

import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ParticipateService participateService;

	/**
	 * 查询活动
	 * @param queryModel
	 * @return
	 */
	@RequestMapping("/searchActivity.do")
	public @ResponseBody SearchActivityResultModel searchActivity(@RequestBody SearchActivityQueryModel queryModel){
		queryModel.setDeadline(new Date());
		return activityService.searchActivity(queryModel);
	}
	@RequestMapping("/createActivity.do")
	public @ResponseBody Map<String,Object> createActivity(String content,
														   String comment,
														   Date startTime,
														   Date endTime,
														   Date deadline,
														   Integer maxNum,
														   Boolean isPublic,
														   Double distance,
														   String creatorId,
														   Double longitude,
														   Double latitude,
														   String locationDescription){
		Activity activity = new Activity();
		Random random = new Random();
		int categoryId = random.nextInt(13) + 1;
		activity.setCategoryId(categoryId+"");
		activity.setContent(content);
		activity.setComment(comment);
		activity.setStartTime(startTime);
		activity.setEndTime(endTime);
		activity.setDeadline(deadline);
		activity.setMaxNum(maxNum);
		activity.setPublic(isPublic);
		activity.setDistance(distance);
		activity.setCreatorId(creatorId);
		Location location = new Location();
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setLocationDescription(locationDescription);
		activity.setActivityLocation(location);
		Activity resultActivity = activityService.createActivity(activity);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(resultActivity!=null){
			map.put("code",200);
			map.put("activityId",resultActivity.getActivityId());
		}else{
			map.put("code",500);
		}
		return map;
	}

	@RequestMapping("/getActivityInfoById.do")
	public @ResponseBody Map<String,Object> getActivityInfoById(String activityId){
		Activity activity = activityService.findActivityById(activityId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(activity!=null){
			map.put("code",200);
			map.put("activity",activity);
			return map;
		}
		map.put("code",500);
		return map;
	}
//	/**
//	 * 创建活动,并校验数据.网页版
//	 *
//	 * @param activity
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/createActivity.do")
//	public String createActivity(@ModelAttribute Activity activity, BindingResult bindingResult, Model model, HttpSession session) {
//		ActivityValidator activityValidator = new ActivityValidator();
//		activityValidator.validate(activity, bindingResult);
//		System.out.println("createActivity.do");
//		if(bindingResult.hasErrors()){
//			System.out.println("error!");
//			Log log = LogFactory.getLog(ActivityController.class);
//			log.info("Code:" + bindingResult.getFieldErrorCount()+" " + bindingResult.getFieldError().getCode());
//			System.out.println("出错了！！！！！！！！");
//			return "activity/createActivity";
//		}
//		User user = (User) session.getAttribute("user");
//		activity.setCreatorId(user.getUserId());
//		activity.setCreator(user);
//		activityService.createActivity(activity);
//		model.addAttribute("activity", activity);
//		return "activity/createSuccess";
//	}

//	/**
//	 * 通过搜索框搜索
//	 *
//	 * @return
//	 */
//	@RequestMapping("/queryActivity.do")
//	public @ResponseBody SearchActivityResultModel queryActivity(SearchActivityQueryModel queryModel) {
//		return activityService.searchActivity(queryModel);
//	}
//
//	@RequestMapping("/searchActivity.do")
//	public String searchActivity(Location location, Model model) {
//		if (location.getLatitude() == null && location.getLongitude() == null) {
//			throw new IllegalArgumentException("位置为空！");
//		}
////		System.out.println(location.getLatitude() + " " + location.getLongitude());
//		SearchActivityResultModel defaultSearchActivity = activityService.defaultSearchActivity(location);
//		model.addAttribute("activities", defaultSearchActivity);
//		return "activity/listActivities";
//	}
//
//	@RequestMapping("/activityInput.do")
//	public String activityInput(Model model){
//		model.addAttribute(new Category());
//		model.addAttribute("activity",new Activity());
//		return "activity/createActivity";
//	}
//
//	@RequestMapping("/shareActivity.do")
//	public String shareActivity(String id){
//		System.out.println(id);
//		return "";
//	}

	/**
	 * 通过活动id删除活动，并跳到删除成功页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteActivity.do")
	public String deleteActivity(String id){
		System.out.println(id);
		activityService.deleteActivityById(id);
		return "activity/deleteSuccess";
	}

	/**
	 *
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/lookActivity.do")
	public String lookActivity(String id,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		Activity activity = activityService.findActivityById(id);
		model.addAttribute("activity",activity);
		if(activity.getCreatorId().equals(user.getUserId())){
			//查看我创建的活动
			return "activity/activityInfoMyCreator";
		}else if(activity.getParticipate().contains(user)){
			//查看我加入的活动
			return "activity/activityInfoMyJoin";
		}else{
			//查看我为加入的活动
			return "activity/activityInfo";
		}
	}

	@RequestMapping("/deleteParticipateById.do")
	public @ResponseBody Boolean deleteParticipateById(String activityId,String userId){

		return participateService.deleteParticipateById(activityId,userId);
	}
}
