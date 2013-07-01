LOCAL_PATH := $(call my-dir)

###########################
#
# SDL shared library
#
###########################

include $(CLEAR_VARS)

LOCAL_MODULE := hello
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
LOCAL_SRC_FILES := hello.c #test/testgles.c src/main/android/SDL_android_main.cpp #src/test/testgles.c
LOCAL_SHARED_LIBRARIES:=SDL
LOCAL_CFLAGS += -DGL_GLEXT_PROTOTYPES
LOCAL_LDLIBS := -ldl -lGLESv1_CM -lGLESv2 -llog

include $(BUILD_EXECUTABLE)
