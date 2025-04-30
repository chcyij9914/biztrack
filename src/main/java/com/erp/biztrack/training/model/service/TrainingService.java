package com.erp.biztrack.training.model.service;

import java.util.ArrayList;
import org.springframework.core.annotation.MergedAnnotations.Search;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;

public interface TrainingService {

	// 전체 교육 글 개수 조회
	int selectListCount();

	// 페이징 적용된 교육 목록 조회
	ArrayList<Training> selectList(Paging paging);

	// 특정 교육 상세 조회
	Training selectTraining(int trainingId);

	// Ajax 테스트용: 마지막 등록된 교육 조회
	Training selectList();

	// ------------------------ dml ----------------------------------------

	// 새 교육 등록
	int insertTraining(Training training);

	// 교육 글 삭제
	int deleteTraining(int trainingId);

	// 교육 글 수정
	int updateTraining(Training training);

	// ---------------------- 검색 관련 ---------------------------

	
	// 교육 제목 검색 목록
	ArrayList<Training> selectSearchTitle(Search search);

	// 교육 내용 검색 목록
	ArrayList<Training> selectSearchContent(Search search);

	// 교육 등록일 검색 목록
	ArrayList<Training> selectSearchDate(Search search);

	ArrayList<Training> selectAll();

}
