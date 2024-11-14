package se.classloader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
public class CustomClassLoader extends ClassLoader {

    // 从指定目录加载jar包
    private static final String PATH = "/Users/kmj/WorkSpace/git/JavaEngineerAndLib/JavaSE/src/main/resources/lib";

    public CustomClassLoader(String jarfileDir) {
        super(null); // 指定父类加载器为 null
        if (StringUtils.isEmpty(jarfileDir)) {
            throw new IllegalArgumentException("basePath can not be empty!");
        }
        File dir = new File(jarfileDir);
        if (!dir.exists()) {
            throw new IllegalArgumentException("basePath not exists:" + jarfileDir);
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("basePath must be a directory:" + jarfileDir);
        }
        loadClassFromJarfileDir(jarfileDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return super.findClass(name);
    }

    private void loadClassFromJarfileDir(String jarfileDir) {
        File[] files = new File(jarfileDir).listFiles();
        if (files != null) {
            for (File file : files) {
                scanJarFile(file);
            }
        }
    }

    private void scanJarFile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.getName().endsWith(".jar")) {
                try {
                    readJAR(new JarFile(file));
                } catch (IOException e) {
                    log.error("", e);
                }
            } else if (file.isDirectory()) {
                for (File f : Objects.requireNonNull(file.listFiles())) {
                    scanJarFile(f);
                }
            }
        }
    }

    private void readJAR(JarFile jar) throws IOException {
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (name.endsWith(".class")) {
                String className = name.replace("\\", ".")
                        .replace("/", ".")
                        .replace(".class", "");
                try (InputStream input = jar.getInputStream(je); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    int bytesNumRead = 0;
                    while ((bytesNumRead = input.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesNumRead);
                    }
                    defineClass(className, baos.toByteArray(), 0, baos.toByteArray().length);
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }
    }


    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader(PATH);
    }
}
