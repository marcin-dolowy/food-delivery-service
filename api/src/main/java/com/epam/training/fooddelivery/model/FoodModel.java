package com.epam.training.fooddelivery.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FoodModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-27T14:59:06.725885800+02:00[Europe/Warsaw]")
public class FoodModel   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("calorie")
  private Integer calorie;

  @JsonProperty("category")
  private String category;

  @JsonProperty("price")
  private Integer price;

  @JsonProperty("description")
  private String description;

  public FoodModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public FoodModel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "Fideua", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FoodModel calorie(Integer calorie) {
    this.calorie = calorie;
    return this;
  }

  /**
   * Get calorie
   * @return calorie
  */
  @ApiModelProperty(example = "553", value = "")


  public Integer getCalorie() {
    return calorie;
  }

  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }

  public FoodModel category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @ApiModelProperty(example = "MEAL", value = "")


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public FoodModel price(Integer price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @ApiModelProperty(example = "10", value = "")


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public FoodModel description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(example = "Fideua is a meal", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodModel foodModel = (FoodModel) o;
    return Objects.equals(this.id, foodModel.id) &&
        Objects.equals(this.name, foodModel.name) &&
        Objects.equals(this.calorie, foodModel.calorie) &&
        Objects.equals(this.category, foodModel.category) &&
        Objects.equals(this.price, foodModel.price) &&
        Objects.equals(this.description, foodModel.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, calorie, category, price, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FoodModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    calorie: ").append(toIndentedString(calorie)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

