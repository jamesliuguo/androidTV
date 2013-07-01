LOCAL_PATH := $(call my-dir)

###########################
#
# SDL shared library
#
###########################

include $(CLEAR_VARS)

LOCAL_MODULE := hello
LOCAL_C_INCLUDES := ./include
LOCAL_SRC_FILES := src/main/android/SDL_android_main.cpp ./src/core/android/SDL_android test/testgles.c
LOCAL_SHARED_LIBRARIES:=SDL2
LOCAL_CFLAGS += -DGL_GLEXT_PROTOTYPES 
LOCAL_LDLIBS := -lGLESv1_CM -lGLESv2 -llog -shared

#include $(BUILD_EXECUTABLE)
include $(BUILD_SHARED_LIBRARY)
