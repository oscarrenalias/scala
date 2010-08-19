package net.renalias {
package model {
	
import scala.List
import net.liftweb.util.FieldError
import net.liftweb.couchdb._ 
import net.liftweb.record.field._
import net.liftweb.common._
import net.liftweb.json.JsonAST.{JField, JInt, JObject, JString, render}
                  
class Guestbook extends CouchRecord[Guestbook]{
 def meta = Guestbook

 object user extends StringField(this, 50) {
   override def validators =
    validLength _ ::
      super.validators
   def validLength(in: Box[String]): List[FieldError] = {
    val inString = in.open_!
    if (inString.size >= 3) Nil
    else List(FieldError(this, <b>User cannot be empty</b>))
   }
 }

 object message extends StringField(this, 1024) {
   override def validators =
    validLength _ ::
      super.validators
   def validLength(in: Box[String]): List[FieldError] = {
    val inString = in.open_!
    if (inString.size >= 1) Nil
    else List(FieldError(this, <b>The message cannot be empty</b>))
   }	
  }
 }
 
object Guestbook extends Guestbook with CouchMetaRecord[Guestbook] {
  def createRecord = new Guestbook

  /*def findAll(tempOwnerBox:Box[User], excludeDone:Boolean):List[Guestbook] = {
    val tempUser = tempOwnerBox.open_!
    val viewReturn = Guestbook.queryView("guestbook", "guestbook_findAll", _.key(JString(tempUser.id.toString)))
    viewReturn match {
      case Full(v) => {
        if(excludeDone){
          return v.toList.filter(_.done.value == false)
        }
        return v.toList
      }
      case Empty => return Nil
    }
  }*/

  def findAll: List[Guestbook] = {
    val viewReturn = Guestbook.queryView("guestbook", "guestbook_findAll")
    viewReturn match {
      case Full(v) =>  return v.toList
      case Empty => return Nil
    }
  }
 }

}}