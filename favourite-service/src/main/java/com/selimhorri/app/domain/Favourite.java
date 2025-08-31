package com.marketplace.platform.wishlist.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.marketplace.platform.wishlist.core.constant.WishlistConstant;
import com.marketplace.platform.wishlist.core.entity.id.WishlistItemId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_wishlist_items")
@IdClass(WishlistItemId.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public final class WishlistItem extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "customer_id", nullable = false)
	private Integer customerId;
	
	@Id
	@Column(name = "item_id", nullable = false)
	private Integer itemId;
	
	@Id
	@Column(name = "added_timestamp", nullable = false)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(pattern = WishlistConstant.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = WishlistConstant.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime addedTimestamp;
	
}










