package wangjing.shareprefrenceutil.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import wangjing.shareprefrenceutil.global.GB;
import wangjing.shareprefrenceutil.task.Task;
import wangjing.shareprefrenceutil.task.TaskUtils;

/**
 * 文件操作
 */
public class FileUtils {

    public static boolean storageIsWritable() {
        boolean externalStorageAvailable = false;
        boolean externalStorageWritable = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            externalStorageAvailable = externalStorageWritable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            externalStorageAvailable = true;
            externalStorageWritable = false;
        } else {
            externalStorageAvailable = externalStorageWritable = false;
        }
        return externalStorageAvailable && externalStorageWritable;
    }

    public static boolean makeDirUnderExternalStorage(String relativePath) {
        String externalStorageDir = getExternalStorageDir();
        String logDirectory = combineFilePath(externalStorageDir, relativePath);
        File newDir = new File(logDirectory);
        if (newDir.exists()) {
            return true;
        }
        return newDir.mkdirs();
    }

    public static String combineFilePath(String dirPath, String fileName) {
        if (StringUtils.isEmptyOrNull(dirPath)) {
            return "";
        }
        if (StringUtils.isEmptyOrNull(fileName)) {
            return "";
        }
        File root = new File(dirPath);
        do {
            if (fileName.startsWith("./")) {
                fileName = fileName.substring(2, fileName.length());
                continue;
            }
            if (fileName.startsWith("../")) {
                fileName = fileName.substring(3, fileName.length());
                root = root.getParentFile();
                continue;
            }
            break;
        } while (true);
        File file2 = new File(root, fileName);
        return file2.getAbsolutePath();
    }

    public static String getExternalStorageDir() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return externalStorageDirectory.getAbsolutePath();
    }

    public static final String FILE_BINARY_MIME = "application/octet-stream";

    private static Map<String, String> FILE_MIMES = new HashMap<>();

    static {
        FILE_MIMES.put(".bmp", "image/bmp");
        FILE_MIMES.put(".gif", "image/gif");
        FILE_MIMES.put(".jpe", "image/jpeg");
        FILE_MIMES.put(".jpeg", "image/jpeg");
        FILE_MIMES.put(".jpg", "image/jpeg");
        FILE_MIMES.put(".png", "image/png");
        FILE_MIMES.put(".speex", "audio/speex");
        FILE_MIMES.put(".spx", "audio/speex");
        FILE_MIMES.put(".zip", "application/zip");
    }

    public static String getFileMime(String fileName) {
        Set<String> keys = FILE_MIMES.keySet();
        for (String key : keys) {
            if (fileName.toLowerCase(Locale.getDefault()).endsWith(key)) {
                return FILE_MIMES.get(key);
            }
        }
        return "*/*";
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
    }

    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static boolean isFileExists(String fileName) {
        if (fileName == null) return false;
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean isDirectory(String fileName) {
        if (fileName == null) return false;
        File file = new File(fileName);
        return file.exists() && file.isDirectory();
    }

    public static void mkdirs(String dirstr) {
        File dir = new File(dirstr);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }

        if (file.exists()) {
            file.delete();
        }
    }

    public static void deleteFile(String fileName) {
        if (fileName == null) {
            return;
        }

        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static String logStr = "FileUtils";
    public static void deleteDir(String dir) {
        Log.i(logStr,"FFFFF - deleteDir(String dir) : " + dir);
        if (StringUtils.isEmptyOrNull(dir)) {
            return;
        }
        if (dir.endsWith(File.separator)) {
            dir = dir.substring(0, dir.length() - 1);
        }
        File file = new File(dir);
        if (!file.exists()) {
            Log.i(logStr,"delete file " + dir + " is not exist!");
            return;
        }
        if (file.isFile()) {
            boolean result = file.delete();
            if (!result) {
                Log.e(logStr,"delete file " + dir + " error : false");
            }
            return;
        }

        File temp = new File(GB.getCallBack().getImgCacheDir() + "cache_file/" + System.currentTimeMillis() + Math.random());
        mkdirParentDir(temp);
        boolean result = file.renameTo(temp);
        if (result) {
            cleanFlipbookTempFile(dir);
        } else {
            Log.e(logStr,"rename & delete file " + dir + " error : false");
        }
    }

    /**
     * @param file
     * @return
     * @description 创建父文件夹
     */
    public static boolean mkdirParentDir(File file) {
        File parentDir = file.getParentFile();
        if (null != parentDir && !parentDir.exists()) {
            if (!parentDir.mkdirs())
                return false;
        }
        return true;
    }

    private static void cleanFlipbookTempFile(final String tempDir) {
        TaskUtils.createSerialExecutor("deleteTemp").post(new Task() {

            @Override
            public void run() throws Exception {
                File temp = new File(GB.getCallBack().getImgCacheDir() + "cache_file/");
                if (temp.exists()) {
                    deleteDirectoryAndFiles(temp);
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static void deleteDirectoryAndFiles(File rootDirectory) {
        if (!rootDirectory.exists()) {
            return;
        }

        File[] files = rootDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryAndFiles(file);
                } else {
                    file.delete();
                }
            }
        }
        rootDirectory.delete();
    }

    /**
     * 得到外部存储可用的空间
     *
     * @return 剩余空间的大小，单位是Byte
     */
    public static long getExternalStoreAvailableSize() {
        if (storageIsWritable()) {
            // 取得sdcard文件路径
            File pathFile = Environment.getExternalStorageDirectory();
            android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
            // 获取SDCard上每个block的SIZE
            long nBlocSize = 0;
            long nAvailaBlock = 0;
            if (Build.VERSION.SDK_INT > 17) {
                nBlocSize = statfs.getBlockSizeLong();
                nAvailaBlock = statfs.getAvailableBlocksLong();
            } else {
                nBlocSize = statfs.getBlockSize();
                nAvailaBlock = statfs.getAvailableBlocks();
            }
            // 获取可供程序使用的Block的数量
            // 计算 SDCard 剩余大小Byte
            return nAvailaBlock * nBlocSize;
        }
        return 0;
    }

    /**
     * @param source
     * @param target
     * @description 最快的复制文件方法
     */
    public static void nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(inStream);
            IOUtils.close(in);
            IOUtils.close(outStream);
            IOUtils.close(out);
        }
    }

    /**
     * @param srcPath
     * @param destPath
     * @return
     * @description 重命名文件
     */
    public static boolean renameFile(String srcPath, String destPath) {
        if (StringUtils.isEmptyOrNull(srcPath) || StringUtils.isEmptyOrNull(destPath)) {
            return false;
        }

        File file = new File(srcPath);
        if (!file.exists()) {
            return false;
        }

        file.renameTo(new File(destPath));
        return true;
    }

    public static List<String> getPaths(String path) {
        return getPaths(new File(path));
    }

    /**
     * @description 遍历得到文件目录下的所有文件, 路径集合
     */
    public static List<String> getPaths(File file) {
        List<String> resultFileName = new ArrayList<>();
        File[] files = file.listFiles();
        if (files == null)
            return resultFileName;// 判断目录下是不是空的
        for (File f : files) {
            if (f.isDirectory()) {// 判断是否文件夹
                resultFileName.add(f.getPath());
                getPaths(f);// 调用自身,查找子目录
            } else
                resultFileName.add(f.getPath());
        }
        return resultFileName;
    }

    public static Uri getUriForFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= 24) {// 7.0及以后文件选择发生变化，判断处理
            return FileProvider.getUriForFile(context, GB.getCallBack().getApplicationId(), file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
