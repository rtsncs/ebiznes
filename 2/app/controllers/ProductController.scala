package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable.ListBuffer
import models.Product
import services.Database

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents, val db: Database) extends BaseController {

  def showAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(db.products))
  }
  
  def showById(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.products.find(_.id == id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("error" -> "Product not found"))
    }
  }

  def add() = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product")),
      product => {
        db.categories.exists(_.id == product.categoryId) match {
          case true =>
            db.products += product
            Created(Json.toJson(product))
          case false => BadRequest(Json.obj("error" -> "Invalid category"))
        }
      })
  }

  def update(id: Int) = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[Product].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid product")),
      updatedProduct => {
        db.products.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> "Product not found"))
          case index =>
            db.categories.exists(_.id == updatedProduct.categoryId) match {
              case true =>
                db.products.update(index, updatedProduct)
                Ok(Json.toJson(updatedProduct))
              case false => BadRequest(Json.obj("error" -> "Invalid category"))
            }
        }
      })
  }

  def delete(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.products.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> "Product not found"))
      case index =>
        db.products.remove(index)
        NoContent
    }
  }
}
