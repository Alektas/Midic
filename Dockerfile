FROM gradle:7.4-jdk11

ENV DEBIAN_FRONTEND=noninteractive

ENV ANDROID_SDK_HOME="/opt/android/sdk"
ENV ANDROID_SDK_ROOT="/opt/android/sdk"
ENV ANDROID_SDK="/opt/android/sdk"
ENV ANDROID_CMDLINE_TOOLS="$ANDROID_SDK/cmdline-tools"
ENV ANDROID_SDK_TOOLS_VERSION="8092744"
ENV ANDROID_SDK_URL="https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_TOOLS_VERSION}_latest.zip"

RUN apt-get update --yes \
    && apt-get install sudo \
    && apt-get install --yes jq

RUN mkdir -p "$ANDROID_CMDLINE_TOOLS" \
    && curl -o sdk.zip $ANDROID_SDK_URL \
    && unzip sdk.zip -d $ANDROID_SDK_ROOT \
    && rm sdk.zip

ENV PATH="${ANDROID_SDK_ROOT}/emulator:${ANDROID_SDK_ROOT}/platform-tools:${ANDROID_SDK_ROOT}/tools/bin:${ANDROID_CMDLINE_TOOLS}/bin:${PATH}"

RUN yes | sdkmanager --licenses --sdk_root=$ANDROID_SDK_ROOT
RUN yes | sdkmanager --update --sdk_root=$ANDROID_SDK_ROOT

COPY packages.txt $ANDROID_SDK_ROOT/
RUN sdkmanager --install --sdk_root=$ANDROID_SDK_ROOT --package_file=$ANDROID_SDK_ROOT/packages.txt

RUN chmod +x -R $ANDROID_SDK_ROOT
RUN sudo chown -R $(whoami) /opt/android/sdk
