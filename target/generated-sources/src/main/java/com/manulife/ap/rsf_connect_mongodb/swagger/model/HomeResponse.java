package com.manulife.ap.rsf_connect_mongodb.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.manulife.ap.rsf_connect_mongodb.swagger.model.Doctor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomeResponse
 */
@Validated

public class HomeResponse  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("answerEntity")
  @Valid
  private List<Doctor> answerEntity = null;

  public HomeResponse answerEntity(List<Doctor> answerEntity) {
    this.answerEntity = answerEntity;
    return this;
  }

  public HomeResponse addAnswerEntityItem(Doctor answerEntityItem) {
    if (this.answerEntity == null) {
      this.answerEntity = new ArrayList<Doctor>();
    }
    this.answerEntity.add(answerEntityItem);
    return this;
  }

  /**
   * Get answerEntity
   * @return answerEntity
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Doctor> getAnswerEntity() {
    return answerEntity;
  }

  public void setAnswerEntity(List<Doctor> answerEntity) {
    this.answerEntity = answerEntity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomeResponse homeResponse = (HomeResponse) o;
    return Objects.equals(this.answerEntity, homeResponse.answerEntity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answerEntity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomeResponse {\n");
    
    sb.append("    answerEntity: ").append(toIndentedString(answerEntity)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

