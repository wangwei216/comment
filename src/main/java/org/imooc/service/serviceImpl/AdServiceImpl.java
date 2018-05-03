package org.imooc.service.serviceImpl;

import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.imooc.dto.AdDto;
import org.imooc.service.AdService;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    //这个是广告图片的保存路径地址
    @Value("${adImage.savePath}")
    private String adImageSavePath;
    //这个死
    @Value("${adImage.url}")
    private String adImageUrl;
    /*
    * 这个是service层去调用Dao层的数据，功能就是添加广告实体
    * */
    @Override
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        /*
        * 到这里需要对dao层拿到的数据进行判断
        * 1.判断是否为空 且拿到的数据文件流还得是大于0
        * 2.判断文件夹是不是为空，如果为空的就再去创建
        * */
        if (adDto.getImgFile()!=null &&adDto.getImgFile().getSize()>0){

            String fileName = System.currentTimeMillis()+ "_" +adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath+ fileName);
            File fileFolder = new File(adImageSavePath);
            //判断文件夹是否存在
         if (!fileFolder.exists()){
             fileFolder.mkdirs();
         }

         try {

                adDto.getImgFile().transferTo(file);
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            }
            catch (IOException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }


    /*
     * 这个实现的是查询广告实体的列表
     * 因为用到了DTO，最后就是需要把你拿到的原本实体都给放到实体的DTO中(这个DTO中只含有原本实体的部分字段)
     * */
    @Override
    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        Ad condition = new Ad();
        //这个用的是spring的工具类把adDTO里面的数据都能复制到当前创建的Ad实体中
        BeanUtils.copyProperties(adDto,condition);
        //然后这里调用dao层的数据去拿到Ad实体类的列表信息
        List<Ad> adList = adDao.selectByPage(condition);
        //然后遍历从dao层拿到的数据
        for (Ad ad : adList){
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }
    /*
     * 通过id查询并且删除广告数据
     *  1.先拿到参数的id去做查询
     *  2.通过id直接去删除
     *  3.然后判断返回结果
     * */
    @Override
    public boolean remove(Long id) {
        Ad ad = adDao.selectById(id);
        int deleteRows = adDao.delete(id);
        FileUtil.delete(adImageSavePath+ad.getImgFileName());
        //然后判断影响的行数是不是为1
        return deleteRows==1;
    }
    /*
    * 因为你要删除广告实体数据的话，必须要先查询出那个id的对象
    * */
    @Override
    public AdDto getById(Long id) {
        AdDto result = new AdDto();
        Ad ad = adDao.selectById(id);
        //这个是把从dao层拿到的数据赋值到DTO里面
        BeanUtils.copyProperties(ad,result);
        result.setImg(adImageUrl+ad.getImgFileName());
        //把图片名字和路径从新设置进去，然后返回到DTO里面
        return result;
    }
    /*
    * 这个是修改实体信息
    *   1.首先先定一个Ad实体
    *   2.通过spring的工具类把从controller层拿到的数据赋值到这个Ad实体
    *   3.判断从DTO中拿到的数据是不是为空且拿到的文件流长度大于0
    *
    *
    *
    * */
    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);
        String fileName = null;
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(), adImageSavePath);
                ad.setImgFileName(fileName);
            } catch (IllegalStateException | IOException e) {
                // TODO 需要添加日志
                return false;
            }
        }
        int updateCount = adDao.update(ad);
        if (updateCount != 1) {
            return false;
        }
        if (fileName != null) {
            return FileUtil.delete(adImageSavePath + adDto.getImgFileName());
        }
        return true;
    }

}
