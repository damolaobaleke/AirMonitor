package com.softroniiks.airmonitor

import com.softroniiks.airmonitor.models.Routes

//only classes within a sealed class file can inherit from it
sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen(Routes.DETAIL_SCREEN)
    object InputScreen: Screen(Routes.INPUT_SCREEN)

    fun routeWithOptArgs(vararg arguments: String): String{
        return buildString {
            append(route).append("?")

            arguments.forEach { arg->
               append(arg).append("='{'$arg'}',")
            }
        }
    }

    fun routeWithMandatoryArgs(vararg arguments: String): String{
        return buildString {
            append(route)
            arguments.forEach { arg->
                append("/$arg")
            }
        }
    }
}