package com.erp.biztrack.training.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dao.TrainingDao;
import com.erp.biztrack.training.model.dto.Training;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingDao trainingDao;

    @Override
    public int selectListCount() {
        return trainingDao.selectListCount();
    }

    @Override
    public ArrayList<Training> selectList(Paging paging) {
        return trainingDao.selectList(paging);
    }

    @Override
    public ArrayList<Training> selectSearchTitle(Search search) {
        return trainingDao.selectSearchTitle(search);
    }

    @Override
    public ArrayList<Training> selectSearchContent(Search search) {
        return trainingDao.selectSearchContent(search);
    }

    @Override
    public ArrayList<Training> selectSearchDate(Search search) {
        return trainingDao.selectSearchDate(search);
    }

    @Override
    public Training selectTraining(int trainingId) {
        return trainingDao.selectTraining(trainingId);
    }

    @Override
    public int insertTraining(Training training) {
        return trainingDao.insertTraining(training);
    }

    @Override
    public int deleteTraining(int trainingId) {
        return trainingDao.deleteTraining(trainingId);
    }

    @Override
    public int updateTraining(Training training) {
        return trainingDao.updateTraining(training);
    }

    @Override
    public Training selectList() {
        return trainingDao.selectList();
    }

	@Override
	public ArrayList<Training> selectAll() {
		return  null;
	}

    
}