package com.raisonne.bd;

import java.util.List;
import java.util.Map;

import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;

public interface ScrollerUtil {
	public List<BloodRequestDTO> getScrollerData();  //
	public Map<Integer, BloodRequestScrollerDTO> getScrollerLlistData();
}
