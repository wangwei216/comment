package org.imooc.service.serviceImpl;

import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.imooc.dto.AdDto;
import org.imooc.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;
    //这个是广告图片的保存路径地址
    @Value("${adImage.savePath}")
    private String adImageSavePath;
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
}
