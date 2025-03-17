package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable.ListBuffer
import models.CartProduct
import services.Database

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents, val db: Database) extends BaseController {

  def showAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(db.cartProducts))
  }
  
  def showById(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.cartProducts.find(_.id == id) match {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(Json.obj("error" -> "Cart item not found"))
    }
  }
  
  def add() = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[CartProduct].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart item")),
      product => {
        db.products.exists(_.id == product.productId) match {
          case true =>
            db.cartProducts += product
            Created(Json.toJson(product))
          case false => BadRequest(Json.obj("error" -> "Invalid product"))
        }
      })
  }

  def update(id: Int) = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[CartProduct].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid cart item")),
      updatedProduct => {
        db.cartProducts.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> "Cart item not found"))
          case index =>
          db.products.exists(_.id == updatedProduct.productId) match {
            case true =>
              db.cartProducts.update(index, updatedProduct)
              Ok(Json.toJson(updatedProduct))
            case false => BadRequest(Json.obj("error" -> "Invalid product"))
          }
        }
      })
  }

  def delete(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.cartProducts.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> "Cart item not found"))
      case index =>
        db.cartProducts.remove(index)
        NoContent
    }
  }
}
