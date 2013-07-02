LOCAL_PATH := $(call my-dir)

###########################
#
# SDL shared library
#
###########################

include $(CLEAR_VARS)

LOCAL_MODULE := SDL2

LOCAL_C_INCLUDES := $(LOCAL_PATH)/SDL2-2.0.0/include

LOCAL_EXPORT_C_INCLUDES := $(LOCAL_C_INCLUDES)

LOCAL_SRC_FILES := \
	$(subst $(LOCAL_PATH)/,, \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/audio/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/audio/android/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/audio/dummy/*.c) \
	$(LOCAL_PATH)/SDL2-2.0.0/src/atomic/SDL_atomic.c \
	$(LOCAL_PATH)/SDL2-2.0.0/src/atomic/SDL_spinlock.c.arm \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/core/android/*.cpp) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/cpuinfo/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/events/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/file/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/haptic/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/haptic/dummy/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/joystick/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/joystick/android/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/loadso/dlopen/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/power/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/power/android/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/render/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/render/*/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/stdlib/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/thread/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/thread/pthread/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/timer/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/timer/unix/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/video/*.c) \
	$(wildcard $(LOCAL_PATH)/SDL2-2.0.0/src/video/android/*.c))

LOCAL_CFLAGS += -DGL_GLEXT_PROTOTYPES
LOCAL_LDLIBS := -ldl -lGLESv1_CM -lGLESv2 -llog

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := hello
LOCAL_C_INCLUDES := $(LOCAL_PATH)/SDL2-2.0.0/include
LCAL_SRC_FILES :=test.cpp 
LOCAL_LDLIBS := -ldl -lGLESv1_CM -lGLESv2 -llog -lSDL2
