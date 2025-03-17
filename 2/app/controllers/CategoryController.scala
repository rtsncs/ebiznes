package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.collection.mutable.ListBuffer
import models.Category
import services.Database

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents, val db: Database) extends BaseController {

  def showAll() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(db.categories))
  }
  
  def showById(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.categories.find(_.id == id) match {
      case Some(category) => Ok(Json.toJson(category))
      case None => NotFound(Json.obj("error" -> "Category not found"))
    }
  }

  def add() = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category")),
      category => {
        db.categories += category
        Created(Json.toJson(category))
      })
  }

  def update(id: Int) = Action(parse.json) { implicit request: Request[JsValue] =>
    request.body.validate[Category].fold(
      _ => BadRequest(Json.obj("error" -> "Invalid category")),
      updatedCategory => {
        db.categories.indexWhere(_.id == id) match {
          case -1 => NotFound(Json.obj("error" -> "Category not found"))
          case index =>
            db.categories.update(index, updatedCategory)
            Ok(Json.toJson(updatedCategory))
        }
      })
  }

  def delete(id: Int) = Action { implicit request: Request[AnyContent] =>
    db.categories.indexWhere(_.id == id) match {
      case -1 => NotFound(Json.obj("error" -> "Category not found"))
      case index =>
        db.categories.remove(index)
        NoContent
    }
  }
}
