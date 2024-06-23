import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class m$b extends AdviceAdapter {
    private final String methodName;
    protected m$b(int api, MethodVisitor methodVisitor, int methodAccess, String methodName, String methodDesc) {
        super(api, methodVisitor, methodAccess, methodName, methodDesc);
        this.methodName = methodName;
    }
    @Override
    protected void onMethodEnter() {
        if (methodName.equals("discoverMods")) {
            // Load "this" pointer
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            // Call hook
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(x$b.class), "d", "(Lnet/fabricmc/loader/impl/discovery/ModDiscoverer;)V", false);
        } else if (methodName.equals("dumpModList")) {
            // Load mod list
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            // Call filter method
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(x$b.class), "f", "(Ljava/util/List;)Ljava/util/List;", false);
            // Store mod list
            mv.visitVarInsn(Opcodes.ASTORE, 1);
        }
    }
}