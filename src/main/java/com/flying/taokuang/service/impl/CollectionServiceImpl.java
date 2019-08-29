package com.flying.taokuang.service.impl;

import com.flying.taokuang.dao.CollectionMapper;
import com.flying.taokuang.dataobject.Collection;
import com.flying.taokuang.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NNShadow
 * @date 2019/8/27 16:26
 */
@Component
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public int deleteByUserIdAndCollectionId(Integer userId, Integer collectionId) {
        return collectionMapper.deleteByUserIdAndCollectionId(userId, collectionId);
    }

    @Override
    public int insert(Collection record) {
        return collectionMapper.insert(record);
    }

    @Override
    public List<Collection> selectByCollectionId(String CollectionId) {
        return collectionMapper.selectByCollectionId(CollectionId);
    }

    @Override
    public List<Collection> selectByCollectorId(int collectorId) {
        return collectionMapper.selectByCollectorId(collectorId);
    }

    @Override
    public int update(Collection record) {
        return collectionMapper.update(record);
    }
}
