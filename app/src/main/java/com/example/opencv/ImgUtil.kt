package com.example.opencv

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.widget.ImageView
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class ImgUtil {

    companion object {

        private const val TAG = "ImgUtil"

        /** Drawable 을 Bitmap 으로 변환 함수 */
        fun Drawable.toBitmap(): Bitmap? {
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(bitmap)
            setBounds(0, 0, canvas.width, canvas.height)
            draw(canvas)
            return bitmap
        }

        /** Mat 을 Bitmap 으로 변환 함수 */
        fun Mat.toBitmap(): Bitmap {
            val bmp = Bitmap.createBitmap(this.cols(), this.rows(), Bitmap.Config.ARGB_8888)
            Utils.matToBitmap(this, bmp)
            return bmp
        }

        /** Bitmap 을 Mat 으로 변환 함수 */
        fun Bitmap.toMat(): Mat {
            val mat = Mat()
            val bitmap: Bitmap = copy(Bitmap.Config.ARGB_8888, true)
            Utils.bitmapToMat(bitmap, mat)
            return mat
        }

        fun ImageView.setImageMat(mat: Mat) = setImageBitmap(mat.toBitmap())

        /** 이미지 변환 함수 */
        fun Mat.convertImageType(code: Int): Mat {
            val cvtMat = Mat()
            Imgproc.cvtColor(this, cvtMat, code)
            return cvtMat
        }

        /** 이미지 전역 이진화 함수 */
        fun Mat.convertToBinaryImage(value: Double, type: Int): Mat {
            val binaryMat = Mat()
            Imgproc.threshold(this, binaryMat, value, 255.0, type)
            return binaryMat
        }

        /** 직선 그리기 함수 */
        fun Mat.drawingLine () {
            Imgproc.line(this, Point(15.0, 20.0), Point(300.0, 350.0), Scalar(0.0, 255.0, 0.0), 10, Imgproc.LINE_AA)
        }

        /** 사각형 그리는 함수 */
        fun Mat.drawingRectangle() {
            Imgproc.rectangle(this, Point(130.0, 50.0), Point(300.0, 280.0), Scalar(0.0, 255.0, 0.0))
            val rec = Rect(130, 50, 170, 230)
            Imgproc.rectangle(this, rec, Scalar(0.0, 255.0, 0.0))
        }

        /** 다각형 그리는 함수 */
        fun Mat.drawingPolylines() {
            val points: List<MatOfPoint> = listOf(
                MatOfPoint(Point(208.0, 71.0), Point(421.0, 161.0), Point(226.0, 232.0), Point(332.0, 52.0), Point(363.0, 250.0))
            )
            Imgproc.polylines(this, points, true, Scalar(0.0, 255.0, 0.0))
        }

        /** 원 그리는 함수 */
        fun Mat.drawingCircle() {
            Imgproc.circle(this, Point(130.0, 50.0), 10, Scalar(0.0, 255.0, 0.0))
        }

        /** 외곽선 추출 함수 */
        fun Mat.detectCannyEdge(val1: Double, val2: Double): Mat {
            val mat = Mat()
            Imgproc.Canny(this, mat, val1, val2)
            return mat
        }

        /** 이미지 밝기 조절 함수 */
        fun Mat.setBrightness(additionalBrightness: Int): Mat {

            // 밝기를 스칼라값으로 치환
            val src2 = Scalar.all(additionalBrightness.toDouble())
            val mat = Mat()
            Core.add(this, src2, mat)
            return mat
        }

        /** 두 이미지 사이 차이 구하는 함수 */
        fun getDiffOperation(src1: Mat, src2: Mat): Mat {
            val mat= Mat()
            Core.absdiff(src1, src2, mat)
            return mat
        }

        /** 이미지에서 원하는 색 추출 함수 */
        fun Mat.extractColors(lowerb: Scalar, upperb: Scalar): Mat {
            val mat = Mat()
            Core.inRange(this, lowerb, upperb, mat)

            return mat
        }

    }

}