#!/bin/bash
PATHCENTRAL=`pwd`

NODE=/bin/node
if test -f "$NODE"; then
    echo ==========================
    echo Ja possui Node
    echo ==========================
else
    echo ==========================
    echo Instalando Node
    echo ==========================
    sudo apt install nodejs npm -y
fi

POSTMAN=~/Postman
if [ -d "$POSTMAN" ]; then
    echo ==========================
    echo Ja possui Postman
    echo ==========================
else
    echo ==========================
    echo Instalando Postman
    echo ==========================
    wget --content-disposition https://dl.pstmn.io/download/latest/linux
    POST=`ls | grep postman`
    tar zxf $POST

    mv ./Postman/ ~/

    touch postman.desktop
    echo "[Desktop Entry]" >> postman.desktop
    echo "Encoding=UTF-8" >> postman.desktop
    echo "Version=1.0" >> postman.desktop
    echo "Type=Application" >> postman.desktop
    echo "Terminal=false" >> postman.desktop
    echo "Exec="~/Postman/Postman >> postman.desktop
    echo "Name=Postman" >> postman.desktop
    IMAGE=`ls ~/Postman/app/icons/`
    echo "Icon="~/Postman/app/icons/$IMAGE >> postman.desktop

    sudo mv postman.desktop ~/.local/share/applications/
    rm $POST
fi

JMETER=`ls ~/ | grep apache`
JMETERPATH=~/$JMETER
if [ -d "$JMETERPATH" ]; then
    echo ==========================
    echo Ja possui JMETER
    echo ==========================
else
    echo ==========================
    echo Instalando Jmeter
    echo ==========================
    wget --content-disposition https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.5.tgz
    tar zxf apache-jmeter-*.tgz
    rm apache-jmeter-*.tgz
    mv apache-jmeter-* ~

    touch jmeter.desktop
    echo "[Desktop Entry]" >> jmeter.desktop
    echo "Encoding=UTF-8" >> jmeter.desktop
    echo "Version=1.0" >> jmeter.desktop
    echo "Type=Application" >> jmeter.desktop
    echo "Terminal=false" >> jmeter.desktop
    JMETER=`ls ~/ | grep apache`
    echo "Exec="~/$JMETER/bin/jmeter >> jmeter.desktop
    echo "Name=Jmeter" >> jmeter.desktop
    echo "Icon="~/$JMETER/bin/report-template/content/pages/icon-apache.png >> jmeter.desktop

    sudo mv jmeter.desktop ~/.local/share/applications/
fi

# APPIUM=~/Postman
# if [ -d "$APPIUM" ]; then
# echo ==========================
# echo Instalando Appium
# echo ==========================
# wget --content-disposition https://github.com/appium/appium-desktop/releases/tag/v1.22.3-4/Appium-Server-GUI-linux-1.22.3-4.AppImage
# chmod +x Appium-Server-GUI-linux-*.AppImage


# wget --content-disposition https://github.com/appium/appium-inspector/releases/download/v2022.11.1/Appium-Inspector-linux-2022.11.1.AppImage
# chmod a+x Appium-Inspector-linux-*.AppImage

AndroidStudio=/opt/android-studio
if [ -d "$AndroidStudio" ]; then
    echo ==========================
    echo Ja possui Android Studio
    echo ==========================
else
    echo ==========================
    echo Instalando Android Studio
    echo ==========================
    sudo add-apt-repository ppa:maarten-fonville/android-studio
    sudo apt update
    sudo apt install android-studio
fi