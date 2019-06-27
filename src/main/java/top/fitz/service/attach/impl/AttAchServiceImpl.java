/**
 * Created by IntelliJ IDEA.
 * User: Kyrie
 * DateTime: 2018/8/3 16:24
 **/
package top.fitz.service.attach.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.fitz.constant.ErrorConstant;
import top.fitz.dao.AttAchDao;
import top.fitz.dto.AttAchDto;
import top.fitz.exception.BusinessException;
import top.fitz.model.AttAchDomain;
import top.fitz.service.attach.AttAchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件接口实现
 */
@Service
public class AttAchServiceImpl implements AttAchService {

    @Autowired
    private AttAchDao attAchDao;

    @Override
    @CacheEvict(value = {"attCaches", "attCache"}, allEntries = true, beforeInvocation = true)
    public void addAttAch(AttAchDomain attAchDomain) {
        if (null == attAchDomain)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.addAttAch(attAchDomain);
    }

    @Override
    @Cacheable(value = "attCaches", key = "'atts' + #p0")
    public PageInfo<AttAchDto> getAtts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttAchDto> atts = attAchDao.getAtts();
        PageInfo<AttAchDto> pageInfo = new PageInfo<>(atts);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "attCaches", key = "'attAchByid' + #p0")
    public AttAchDto getAttAchById(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        return attAchDao.getAttAchById(id);
    }

    @Override
    @CacheEvict(value = {"attCaches", "attCache"}, allEntries = true, beforeInvocation = true)
    public void deleteAttAch(Integer id) {
        if (null == id)
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        attAchDao.deleteAttAch(id);
    }
}
