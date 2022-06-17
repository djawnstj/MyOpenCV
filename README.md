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
* params
  * src : 원본 이미지
  * dst : 출력 이미지
  * code : 변환할 이미지 형식

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
    ![thresh](https://user-images.githubusercontent.com/90193598/172132997-4205395c-9937-4198-ab40-ef7ff2824da6.png)
    
<hr />

### 3. line - 직선 그리는 함수

* params
  * img: 그림을 그릴 영상
  * pt1: 시작점 (Point)
  * pt2: 끝점 (Point)
  * color: 선 색상 Scalar(B,G,R)
  * thickness: 선 두께
  * lineType: 선 타입; LINE_8(기본값), LINE_4, LINE_AA
    * LINE_4와 LINE_8은 인접 픽셀을 4방향 중 하나로 채울지 8방향중 하나로 채울지 결정한다.
    * AA는 Anti-Aliasing(계단 현상 방지 기술)의 약자이다.
      ![www charlezz com-opencv-opencv-img1 daumcdn-1-300x293](https://user-images.githubusercontent.com/90193598/173782494-edd1d13e-3f6f-4d04-9edd-7de2b7ac370c.png)
      ![www charlezz com-opencv-opencv-img1 daumcdn-300x133](https://user-images.githubusercontent.com/90193598/173782441-0aae2857-cf37-44dd-aa73-a163289a9323.png)
  * shift: 그리기 좌표 값의 축소 비율. 기본값은 0
  
<hr />

### 4. rectangle - 사각형 그리는 함수

* params
  * img: 그림을 그릴 영상
  * pt1: 시작점 (Point)
  * pt2: 끝점 (Point)
  * rec: 사각형 (Rect)
  * color: 선 색상 Scalar(B,G,R)
  * thickness: 선 두께
  * lineType: 선 타입; LINE_8(기본값), LINE_4, LINE_AA
  * shift: 그리기 좌표 값의 축소 비율. 기본값은 0
  
<hr />

### 5. polylines - 다각형 그리는 함수

* params
  * img: 그림을 그릴 영상
  * pts: 다각형 꼭지점의 배열
  * isClosed: 폐곡선 여부
  * color: 선 색상 Scalar(B,G,R)
  * thickness: 선 두께
  * lineType: 선 타입; LINE_8(기본값), LINE_4, LINE_AA
  * shift: 그리기 좌표 값의 축소 비율. 기본값은 0

<hr />

### 6. 원 그리는 함수

* params
  * img: 그림을 그릴 영상
  * center: 원의 중심 좌표
  * radius: 원의 반지름
  * color: 선 색상 Scalar(B,G,R)
  * thickness: 선 두께
  * lineType: 선 타입; LINE_8(기본값), LINE_4, LINE_AA
  * shift: 그리기 좌표 값의 축소 비율. 기본값은 0

<hr />

### 7. 문자열 그리는 함수

* params
  * img: 원본 이미지
  * text: 출력할 문자열
  * org: 문자열을 출력할 위치의 좌측 하단 좌표
  * fontFace: 폰트 종류
  * fontScale: 폰트 크기 비율
  * bottomLeftOrigin: true 면 좌측 하단을 원점으로 지정. 기본값은 false

<hr />

### 8. 외곽선 추출 함수
이미지 안의 화소치의 변화, 휘도의 변화가 커다란 부분을 검출하여 엣지를 추출하는 이미지 처리

* params
  * image : 입력 영상
  * edges : 출력 영상
  * threshold1 : 최소 임계값
  * threshold2 : 최대 임계값
    ![canny](https://user-images.githubusercontent.com/90193598/174196488-d4a6f07b-687f-45ae-9c88-ffa56b45e482.png)
    
<hr />