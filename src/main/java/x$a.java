import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.CodeSource;

public class x$a {
    public static String modsDir;

    public static void premain(String args, Instrumentation inst) {
        // Check if current vm is a minecraft vm
        String vmName = System.getProperty("sun.java.command");
        if (vmName == null)
            return;
        String VM1 = "net.fabricmc.devlaunchinjector.Main";
        String VM2 = "net.fabricmc.loader.impl.launch.knot.KnotClient";
        if (!vmName.startsWith(VM1) && !vmName.startsWith(VM2))
            return;

        Path jarDir;
        try {
            CodeSource codeSource = x$a.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            jarDir = jarFile.getParentFile().toPath();
        } catch (URISyntaxException e) {
            return;
        }
        Path modsDir = jarDir.resolve("mods");
        try {
            Files.createDirectories(modsDir);
        } catch (IOException e) {
            return;
        }
        x$a.modsDir = modsDir.toString();
        inst.addTransformer(new x$b(), true);
    }
}