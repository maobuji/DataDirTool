# DataDirTool-金蝶EAS数据字典生成工具

## 说明

本工具可根据EAS服务器中的元数据文件，获得数据表的元数据信息，并导出到Excel中生成数据字典。</br>

样例：可使用Excel的数据筛选功能，查看自己关注的数据表<br>
<img src="doc/img/demo.png" width="791" height="422">

* [DataDirTool-V1.2下载](https://github.com/maobuji/DataDirTool/releases/download/1.2/DataDirTool-V1.2.zip)

作者联系方式：16770864@qq.com

## 使用说明

此工具为java开发，需要先安装java运行环境JDK。

工具可能存在bug，为避免风险，请不要直接在服务器上运行此工具，请拷贝对应的服务器文件到本地。


##### 工具使用方法

1. 解压DataDirTool.zip工具包</br>
![image](doc/img/help1.png)
   

2. 拷贝服务器eas/server/metas元数据文件夹到本地</br>
![image](doc/img/help2.png)
   

3. 执行run.bat运行工具</br>
![image](doc/img/help3.png)
   

4. 选择元数据文件夹</br>
![image](doc/img/help4.png)
   

5. 点击生成字典</br>
xlsx若选中则生成2007版的Excel文件，字典不会分页。不选则生成旧版本的Excel数据会拆分成多个sheet页存储</br>
![image](doc/img/help5.png)


6. 生成完毕后，在同级目录下查看生成的字典文件</br>
![image](doc/img/help6.png)

![image](doc/img/help7.png)


## 开发配置

工程为maven工程直接导入即可。由于有一些外部jar无法下载。
故需要在extlib目录中直接install.bat将外部jar加入到本地maven仓库中。

程序主启动类为：com.fan.AppUIStart


## 常见异常说明

1. run.bat一闪就消失了
   请检查jdk是否安装，推荐使用JDK1.8






