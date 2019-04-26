package KTDevelop.Startup

val requestedFileList = mutableListOf<String>()
val parameterList = mutableListOf<String>()

fun setCommandLineArgs(args: Array<String>) {
    requestedFileList.clear()
    parameterList.clear()

    args.forEach {
        if (it[0] == '-' || it[0] == '/') {
            var markerLength = 1
            if (it.length >= 2 && it[0] == '-' && it[1] == '-') {
                markerLength = 2
            }

            val param = it.substring(markerLength)
            parameterList.add(param)
        } else {
            requestedFileList.add(it)
        }
    }
}

fun showSplashScreen(){
    val splashScreen = SplashScreen()
    splashScreen.show()
}

class SplashScreen{
    fun show(){

    }
}
