package com.high.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.high.entity.Activity;

public class ActivityValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Activity.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO 活动信息的验证
		Activity activity = (Activity) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "activity.content.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categotyId", "activity.categotyId.required");
//		Double latitude = activity.getActivityLocation().getLatitude();
//		Double longitude = activity.getActivityLocation().getLongitude();
		ValidationUtils.rejectIfEmpty(errors, "activityLocation.latitude", "activity.activityLocation.latitude.required");
		ValidationUtils.rejectIfEmpty(errors, "activityLocation.longitude", "activity.activityLocation.longitude.required");
		ValidationUtils.rejectIfEmpty(errors, "startTime", "activity.startTime.required");
		
		Date startTime = activity.getStartTime();
		Date endTime = activity.getEndTime();
		if(startTime != null && endTime!=null){
			if(startTime.getTime() > endTime.getTime()){
				errors.rejectValue("startTime", "startTimeMustBeforeEndTime");
			}
		}
	}

}
