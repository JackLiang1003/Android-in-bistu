import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirFile {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\CHIN\\Desktop\\HomeWork\\dir";

        while (true) {
            System.out.println("请输入要执行的操作:1、输入文件名  2、删除文件名  3、合并文件  4、拆分文件");
            Scanner sc = new Scanner(System.in);
            int type = sc.nextInt();
            switch (type){
                case 1:
                    System.out.println("请输入”文件名“");
                    if (sc.hasNext()) {
                        String fileName = sc.next();
                        System.out.println(fileName);
                        //查找文件
                        List<File> fileList = new ArrayList<>();
                        findFiles(filePath, fileName, fileList);
                        System.out.println("查找到的文件" + fileList);
                        if (fileList.size() > 0) { //如果查到了文件
                            fileList.forEach(v->{
                                renameNewFile(v);
                            });
                        } else { //没有查到文件
                            System.out.println("没有该文件，以为您创建该文件");
                            //创建文件
                            File newFile = new File(fileName);
                            System.out.println("请“写入数据”");
                            String inputText = sc.next();
                            FileOutputStream out = new FileOutputStream(newFile);
                            out.write(inputText.getBytes());
                            System.out.println("写入数据成功，读取文件数据");
                            //显示写入的数据
                            showInputText(newFile);
                            //拷贝文件到三个文件夹中
                            File copyFile1 = new File("G:\\HomeWork\\dir\\1\\" + fileName);
                            File copyFile2 = new File("G:\\HomeWork\\dir\\2\\" + fileName);
                            File copyFile3 = new File("G:\\HomeWork\\dir\\3\\" + fileName);
                            copyFileUsingFileChannels(newFile, copyFile1);
                            copyFileUsingFileChannels(newFile, copyFile2);
                            copyFileUsingFileChannels(newFile, copyFile3);
                        }
                    }
                    break;

                case 2:
                    System.out.println("输入想要删除的文件名");
                    String removeFileName = sc.next();
                    List<File> fileList = new ArrayList<>();
                    findFiles(filePath,removeFileName,fileList);
                    if (fileList.size()>0){//查找到了该文件
                        File file = fileList.get(0);
                        System.out.println(file.getAbsolutePath());
                        File copyFile1 = new File("G:\\HomeWork\\dir\\1\\" + removeFileName);
                        File copyFile2 = new File("G:\\HomeWork\\dir\\2\\" + removeFileName);
                        File copyFile3 = new File("G:\\HomeWork\\dir\\3\\" + removeFileName);
                    copyFile1.delete();
                    copyFile2.delete();
                    copyFile3.delete();
                    }else {//没有查找到该文件
                        System.out.println("没有该文件，无法删除");
                    }
                    break;
                case 3:
                    System.out.println("请输入第一个要合并的文件");
                    String firstFileName = sc.next();
                    List<File> firstFileList = new ArrayList<>();
                    findFiles(filePath,firstFileName,firstFileList);
                    File firstFile = firstFileList.get(0);

                    System.out.println("请输入第二个要合并的文件");
                    String towFileName = sc.next();
                    List<File> towFileList = new ArrayList<>();
                    findFiles(filePath,towFileName,towFileList);
                    File towFile = towFileList.get(0);

                    //执行合并
                    merge(firstFile,towFile);
                    break;
                case 4:
                    System.out.println("请输入要拆分的文件");

                    String splitFileName = sc.next();
                    List<File> splitFileList = new ArrayList<>();
                    findFiles(filePath,splitFileName,splitFileList);
                    //读取要拆分文件的内容
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(new FileInputStream(splitFileList.get(0).getAbsolutePath()), "UTF-8"));
                    String str = bufferedReader.readLine();
                    int length = str.length();
                    int i = length / 2;
                    String beginStr = str.substring(0, i);
                    String endStr = str.substring(i, length-1);
                    System.out.println("第一个:"+beginStr);
                    System.out.println("第二个:"+endStr);

                    File splitOneFile = new File(filePath+"\\split\\拆分第一个" + splitFileName);
                    FileOutputStream outOne=new FileOutputStream(splitOneFile);
                    outOne.write(beginStr.getBytes());

                    File splitTowFile = new File(filePath+"\\split\\拆分第二个" + splitFileName);
                    new FileOutputStream(splitTowFile).write(endStr.getBytes());
                    break;
            }

        }
    }

    private static void merge(File firstFile, File towFile) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(firstFile), "UTF-8"));
        String firstText = bufferedReader.readLine();
        BufferedReader bfTow = new BufferedReader(
                new InputStreamReader(new FileInputStream(towFile), "UTF-8"));
        String towText = bfTow.readLine();
        String merge=firstText+towText;
        System.out.println(merge);
        String subFirst = firstFile.getName().substring(0, firstFile.getName().lastIndexOf("."));
        String subTow = towFile.getName().substring(0, towFile.getName().lastIndexOf("."));
        String mergeFile=firstFile.getParent()+"\\"+subFirst+"和"+subTow+"合并.txt";
        System.out.println(mergeFile);
        File file = new File(mergeFile);
        FileOutputStream out = new FileOutputStream(file);
        out.write(merge.getBytes());


    }

    /**
     * 则编写函数将存在的文件“重新命名”为一个新的文件，并将此文件的数据“
     * 读取”“包括路径，名称，数据内容”展示
     */
    public static void renameNewFile(File file) {
        String parent = file.getParent();
        String name = file.getName();
        String modifyName="修改后的文件"+name;
        // 新的文件或目录

        File newName = new File(parent+"\\"+modifyName);
        if (newName.exists()) {  //  确保新的文件名不存在
            System.out.println("新文件名已经存在");
        }
        if(file.renameTo(newName)) {
            System.out.println("已重命名");
            System.out.println("修改后的文件路径:"+parent+"\\"+modifyName);
            System.out.println("修改后的文件名:"+modifyName);
            System.out.println("修改后的文件内容:");
            showInputText(newName);
        } else {
            System.out.println("Error");
        }
    }

    /**
     * 最终编写函数将创建的文件“复制”再“拷贝”到三个文件夹中
     */
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    /**
     * 即编写函数可以
     * 将用户输入的数据显示
     */
    public static void showInputText(File newFile) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(newFile), "UTF-8"));
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 递归查找文件
     *
     * @param baseDirName    查找的文件夹路径
     * @param targetFileName 需要查找的文件名
     * @param fileList       查找到的文件集合
     */
    public static void findFiles(String baseDirName, String targetFileName, List fileList) {

        File baseDir = new File(baseDirName);        // 创建一个File对象
        if (!baseDir.exists() || !baseDir.isDirectory()) {    // 判断目录是否存在
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
        }
        String tempName = null;
        //判断目录是否存在
        File tempFile;
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            if (tempFile.isDirectory()) {
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
            } else if (tempFile.isFile()) {
                tempName = tempFile.getName();
                if (wildcardMatch(targetFileName, tempName)) {
                    // 匹配成功，将文件名添加到结果集
                    fileList.add(tempFile.getAbsoluteFile());
                }
            }
        }
    }

    /**
     * 通配符匹配
     *
     * @param pattern 通配符模式
     * @param str     待匹配的字符串
     * @return 匹配成功则返回true，否则返回false
     */
    private static boolean wildcardMatch(String pattern, String str) {
        int patternLength = pattern.length();
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                //通配符星号*表示可以匹配任意多个字符
                while (strIndex < strLength) {
                    if (wildcardMatch(pattern.substring(patternIndex + 1),
                            str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            } else if (ch == '?') {
                //通配符问号?表示匹配任意一个字符
                strIndex++;
                if (strIndex > strLength) {
                    //表示str中已经没有字符匹配?了。
                    return false;
                }
            } else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }


}
