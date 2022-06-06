OpenCV 를 이용한 Adroid App
===========================

<hr/>

### 1. cvtColor - 이미지 변환 함수

* BGR : Blue, Green, Red 채널 -
* BGRA : Blue, Green, Red, Alpha 채널 -
* RGB : Red, Green, Blue 채널 -
* RGBA : Red, Green, Blue, Alpha 채널 -
* GRAY : 단일 채널 - 그레이스케일
* BGR565 : Blue, Green, Red 채널 - 16 비트 이미지
* XYZ : X, Y, Z 채널 - CIE 1931 색 공간
* YCrCb : Y(휘도 : 밝기), Cr/Cb(색차 : 색상 성분) 채널 - YCC (크로마)
* HSV : Hue, Saturation, Value 채널 - 색상, 채도, 명도(밝기)
* Lab : L, a, b 채널	- 반사율, 색도1, 색도2
* Luv : L, u, v 채널	- CIE Luv
* HLS : Hue, Lightness, Saturation 채널 - 색상, 밝기, 채도
* YUV : Y, U, V 채널	- 밝기, 색상1(밝기와 파란색과의 색상 차), 색상2(밝기와 빨간색과의 색상 차)
* BG, GB, RG : 디모자이킹	- 단일 색상 공간으로 변경
* _EA : 디모자이킹 - 가장자리 인식
* _VNG : 디모자이킹 - 그라데이션 사용

<hr/>

### 2. threshold - 이미지 이진화 함수

* 배경(Background)과 객체(Object) 구분, 관심 영역과 비관심 영역 가르기 위해 사용.
* 일반적으로 gray scale 영상으로 함.
* 영상을 스캔하면서 픽셀값이 임계값(Threshold) 값보다 크면 결과 영상의 같은 위치의 픽셀값을 흰색(255)으로 하고, 픽셀값이 threshold값보다 작으면 검은색(0)으로 한다.
* params
  * src : 원본 이미지
  * dst : 출력 이미지
  * thresh : 임계값
  * maxval : 최대 임계값. 일반적으로 회색조 이미지의 픽셀값은 최대 255를 가지므로 보통 255로 지정한다.
  * type : 임계값 타입

<hr />
