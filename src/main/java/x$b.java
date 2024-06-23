import net.fabricmc.loader.impl.discovery.DirectoryModCandidateFinder;
import net.fabricmc.loader.impl.discovery.ModCandidate;
import net.fabricmc.loader.impl.discovery.ModDiscoverer;
import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.List;

public class x$b implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace('/', '.');
        String c1 = "net.fabricmc.loader.impl.discovery.ModDiscoverer";
        String c2 = "net.fabricmc.loader.impl.FabricLoaderImpl";
        if (className.equals(c1) || className.equals(c2)) {
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            reader.accept(new m$a(writer), ClassReader.EXPAND_FRAMES);
            return writer.toByteArray();
        }
        return classfileBuffer;
    }

    public static void d(ModDiscoverer discoverer)
    {
        discoverer.addCandidateFinder(new DirectoryModCandidateFinder(Paths.get(x$a.modsDir), FabricLauncherBase.getLauncher().isDevelopment()));
    }

    public static List<ModCandidate> f(List<ModCandidate> mods)
    {
        return mods.stream().filter(mod -> !mod.getLocalPath().contains(x$a.modsDir)).toList();
    }
}
