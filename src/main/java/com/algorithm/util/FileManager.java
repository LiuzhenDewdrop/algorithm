package com.algorithm.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.csource.fastdfs.TrackerClient;

import com.jy.fast.isyn.TrackerClientLoader;

/**
 * Created by zhuliyu on 2015/4/9.
 */
public class FileManager extends TrackerClientLoader{

    private static Logger logger  = Logger.getLogger(FileManager.class);

    private static String profixPath ;
    private TrackerClient tracker;
    private String fastTrackerConfig;

    static{
        try {
            profixPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
        }catch (IOException e) {
            logger.error("获取文件跟路径失败"+e.getMessage());
        }
    }

    public FileManager() {
    }

    public TrackerClient getTracker() {
        return super.getTracker();
    }

    public void setFastTrackerConfig(String fastTrackerConfig) {
        super.setFastTrackerConfig(profixPath+ File.separator+fastTrackerConfig);
    }

    public void init() {
       super.init();
    }

}
