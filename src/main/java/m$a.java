import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class m$a extends ClassVisitor {
    public m$a(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }
    @Override
    public MethodVisitor visitMethod(int methodAccess, String methodName, String methodDesc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(methodAccess, methodName, methodDesc, signature, exceptions);
        return new m$b(Opcodes.ASM9, methodVisitor, methodAccess, methodName, methodDesc);
    }
}
