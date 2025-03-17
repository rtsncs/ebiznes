package services

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable.ListBuffer
import models._

@Singleton
class Database () {
  val products: ListBuffer[Product] = ListBuffer(
    Product(1, "Smartphone", 1, 799.99),
    Product(2, "Laptop", 1, 1299.49),
    Product(3, "Headphones", 2, 199.99),
    Product(4, "Smartwatch", 1, 249.99)
  )

  val cartProducts: ListBuffer[CartProduct]= ListBuffer(
    CartProduct(1, 2, 1)
  )

  val categories: ListBuffer[Category] = ListBuffer(
    Category(1, "Electronics"),
    Category(2, "Accessories")
  )
}
