package hdm.pk070.jscheme.obj.builtin.function;

import hdm.pk070.jscheme.error.SchemeError;
import hdm.pk070.jscheme.obj.SchemeObject;
import hdm.pk070.jscheme.obj.builtin.simple.number.SchemeInteger;
import hdm.pk070.jscheme.stack.SchemeCallStack;

/**
 * @author patrick.kleindienst
 */
public final class SchemeBuiltinTimes extends SchemeBuiltinFunction {

    public static SchemeBuiltinTimes create() {
        return new SchemeBuiltinTimes("*");
    }

    private SchemeBuiltinTimes(String internalName) {
        super(internalName);
    }

    @Override
    public SchemeObject call(int argCount) throws SchemeError {
        int product = 1;

        // pop argCount arguments from stack and multiply them
        for (int i = 0; i < argCount; i++) {
            SchemeObject currentArg = SchemeCallStack.instance().pop();
            // check if currentArg is of type SchemeInteger
            if (!currentArg.typeOf(SchemeInteger.class)) {
                throw new SchemeError(String.format("(*): contract violation [expected: number, given: %s]",
                        currentArg));
            }
            // do multiply
            product *= ((SchemeInteger) currentArg).getValue();
        }

        return new SchemeInteger(product);
    }

    @Override
    public String toString() {
        return "<procedure:*>";
    }
}
