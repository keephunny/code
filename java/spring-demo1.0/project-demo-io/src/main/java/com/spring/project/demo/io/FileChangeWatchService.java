/*
 * Copyright (c) 2019.
 */
package com.spring.project.demo.io;

import java.nio.file.*;
/**
 * 文件或文件夹变动监听
 * CREATE DELETE MODIFY
 *
 * @author 汪强
 * 创建时间 2019-09-03 17:58
 */
public class FileChangeWatchService {

    public static void main(String[] args) {
        try {
            //创建一个监听服务
            WatchService service = FileSystems.getDefault().newWatchService();
            //设置路径
            Path path = Paths.get("D:\\data\\drain\\2019\\0903\\");

            //注册监听器
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey watchKey;

            //使用dowhile
            do {
                //获取一个watch key
                watchKey = service.take();
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    //如果时间列表不为空，打印事件内容
                    //可以增加文件名判断 就可以精确的判断哪个文件是否改变
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();
                    System.out.println(eventPath + ":" + kind + ":" + eventPath);
                }
                System.out.println("目录内容发生改变");

            } while (watchKey.reset());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
