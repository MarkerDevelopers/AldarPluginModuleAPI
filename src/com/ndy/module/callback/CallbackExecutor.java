package com.ndy.module.callback;

import com.ndy.module.impl.IModuleCallbackRef;

public class CallbackExecutor {

    private IModuleCallbackRef ref;

    public CallbackExecutor(IModuleCallbackRef ref) { this.ref = ref; }

    public Object execute(Object... objects) { return ref.call(objects); }

}
