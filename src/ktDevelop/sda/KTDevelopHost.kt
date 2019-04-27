package ktDevelop.sda

class KTDevelopHost(appDomain: String, startup: StartupSettings) {
    private var helper: CallHelper

    init {
        helper = CallHelper()
        helper.initKTDevelopCore(CallbackHelper(this), startup)
    }
}
