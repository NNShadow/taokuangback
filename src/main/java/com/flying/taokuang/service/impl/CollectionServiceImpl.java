package com.flying.taokuang.service.impl;

import com.flying.taokuang.dao.CollectionMapper;
import com.flying.taokuang.dao.ContentMapper;
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

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int deleteByUserIdAndCollectionId(Integer userId, Integer collectionId) {
        return collectionMapper.deleteByUserIdAndCollectionId(userId, collectionId);
    }

    @Override
    public int insert(Collection collection) {
        //这个人收藏过这个商品 或 没有这个商品
        if (collectionMapper.selectByCollectionIdAndCollectorId(collection.getCollectionId(), collection.getCollectorId()) != null
                || contentMapper.selectById(collection.getCollectionId()) == null){
            return 0;
        }
        return collectionMapper.insert(collection);
    }

    @Override
    public List<Collection> selectByCollectionId(int CollectionId) {
        return collectionMapper.selectByCollectionId(CollectionId);
    }

    @Override
    public List<Collection> selectByCollectorId(int collectorId) {
        return collectionMapper.selectByCollectorId(collectorId);
    }

    @Override
    public Collection selectByCollectionIdAndCollectorId(int collectionId, int collectorId) {
        return collectionMapper.selectByCollectionIdAndCollectorId(collectionId, collectorId);
    }

    @Override
    public int update(Collection record) {
        return collectionMapper.update(record);
    }
}
