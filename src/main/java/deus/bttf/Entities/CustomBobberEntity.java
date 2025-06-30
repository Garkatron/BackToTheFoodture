package deus.bttf.Entities;


import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityFishingBobber;

import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.MobPathfinder;
import net.minecraft.core.entity.player.Player;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.HitResult;
import net.minecraft.core.util.phys.Vec3;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

import static deus.bttf.Items.BTTFItems.*;


public class CustomBobberEntity extends EntityFishingBobber {
	public static final int ID_BOBBER_BITFIELD = 2;
	private int xTile;
	private int yTile;
	private int zTile;
	public Player owner;
	private int ticksInAir;
	private int ticksCatchable;
	public Entity hookedEntity;
	private int lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;
	private double lerpYRot;
	private double lerpXRot;
	private double velocityX;
	private double velocityY;
	private double velocityZ;

	public CustomBobberEntity(World world) {
		super(world);
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.hookedEntity = null;
		this.setSize(0.25F, 0.25F);
		this.ignoreFrustumCheck = true;
	}

	public CustomBobberEntity(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.setPos(x, y, z);
		this.ignoreFrustumCheck = true;
	}

	public CustomBobberEntity(World world, Player owner) {
		super(world, owner);
		this.ticksInAir = 0;
		this.ticksCatchable = 0;
		this.hookedEntity = null;
		this.ignoreFrustumCheck = true;
		this.owner = owner;
		this.owner.bobberEntity = this;
		this.setSize(0.25F, 0.25F);
		this.moveTo(owner.x, owner.y + 1.62 - (double)owner.heightOffset, owner.z, owner.yRot, owner.xRot);
		this.x -= (double)(MathHelper.cos(this.yRot / 180.0F * 3.1415927F) * 0.16F);
		this.y -= 0.1;
		this.z -= (double)(MathHelper.sin(this.yRot / 180.0F * 3.1415927F) * 0.16F);
		this.setPos(this.x, this.y, this.z);
		this.heightOffset = 0.0F;
		float f = 0.4F;
		this.xd = (double)(-MathHelper.sin(this.yRot / 180.0F * 3.1415927F) * MathHelper.cos(this.xRot / 180.0F * 3.1415927F) * f);
		this.zd = (double)(MathHelper.cos(this.yRot / 180.0F * 3.1415927F) * MathHelper.cos(this.xRot / 180.0F * 3.1415927F) * f);
		this.yd = (double)(-MathHelper.sin(this.xRot / 180.0F * 3.1415927F) * f);
		this.shoot(this.xd, this.yd, this.zd, 1.5F, 1.0F);
	}

	protected void defineSynchedData() {
		this.entityData.define(2, (byte)0, Byte.class);
	}

	public boolean isInGround() {
		return this.entityData.getByte(2) != 0;
	}

	public void setInGround(boolean flag) {
		this.entityData.set(2, Byte.valueOf((byte)(flag ? 1 : 0)));
	}

	public boolean shouldRenderAtSqrDistance(double distance) {
		double d1 = this.bb.getSize() * 4.0;
		d1 *= 64.0;
		return distance < d1 * d1;
	}

	public void shoot(double x, double y, double z, float speed, float randomness) {
		float f2 = MathHelper.sqrt(x * x + y * y + z * z);
		x /= (double)f2;
		y /= (double)f2;
		z /= (double)f2;
		x += this.random.nextGaussian() * 0.0075 * (double)randomness;
		y += this.random.nextGaussian() * 0.0075 * (double)randomness;
		z += this.random.nextGaussian() * 0.0075 * (double)randomness;
		x *= (double)speed;
		y *= (double)speed;
		z *= (double)speed;
		this.xd = x;
		this.yd = y;
		this.zd = z;
		float hAng = MathHelper.sqrt(x * x + z * z);
		this.yRotO = this.yRot = (float)(Math.atan2(x, z) * 180.0 / Math.PI);
		this.xRotO = this.xRot = (float)(Math.atan2(y, (double)hAng) * 180.0 / Math.PI);
	}

	public void lerpTo(double x, double y, double z, float yRot, float xRot, int i) {
		this.lerpX = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.lerpYRot = (double)yRot;
		this.lerpXRot = (double)xRot;
		this.lerpSteps = i;
		this.xd = this.velocityX;
		this.yd = this.velocityY;
		this.zd = this.velocityZ;
	}

	public void lerpMotion(double xd, double yd, double zd) {
		this.velocityX = this.xd = xd;
		this.velocityY = this.yd = yd;
		this.velocityZ = this.zd = zd;
	}

	public void tick() {
		super.tick();
		if (this.lerpSteps > 0) {
			double d = this.x + (this.lerpX - this.x) / (double)this.lerpSteps;
			double d1 = this.y + (this.lerpY - this.y) / (double)this.lerpSteps;
			double d2 = this.z + (this.lerpZ - this.z) / (double)this.lerpSteps;

			double d4;
			for(d4 = this.lerpYRot - (double)this.yRot; d4 < -180.0; d4 += 360.0) {
			}

			while(d4 >= 180.0) {
				d4 -= 360.0;
			}

			this.yRot = (float)((double)this.yRot + d4 / (double)this.lerpSteps);
			this.xRot = (float)((double)this.xRot + (this.lerpXRot - (double)this.xRot) / (double)this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d, d1, d2);
			this.setRot(this.yRot, this.xRot);
		} else {
			double d3;
			if (!this.world.isClientSide) {
				ItemStack heldPlayerItem = this.owner.getCurrentEquippedItem();
				if (this.owner.removed || !this.owner.isAlive() || heldPlayerItem == null || heldPlayerItem.getItem() != Items.TOOL_FISHINGROD || this.distanceToSqr(this.owner) > 1024.0) {
					this.remove();
					this.owner.bobberEntity = null;
					return;
				}

				if (this.hookedEntity != null) {
					if (!this.hookedEntity.removed) {
						this.x = this.hookedEntity.x;
						this.y = this.hookedEntity.bb.minY + (double)this.hookedEntity.bbHeight * 0.8;
						this.z = this.hookedEntity.z;
						if (this.hookedEntity instanceof MobPathfinder) {
							((MobPathfinder) this.hookedEntity).setTarget(this.owner);
						}

						double dx = this.owner.x - this.x;
						double dy = this.owner.y - this.y;
						d3 = this.owner.z - this.z;
						double distance = (double)MathHelper.sqrt(dx * dx + dy * dy + d3 * d3);
						if (distance > 10.0) {
							double scale = 0.01;
							Entity var10000 = this.hookedEntity;
							var10000.xd += dx * scale;
							var10000 = this.hookedEntity;
							var10000.yd += dy * scale;
							var10000 = this.hookedEntity;
							var10000.zd += d3 * scale;
						}

						return;
					}

					this.hookedEntity = null;
				}
			}

			if (this.isInGround()) {
				if (this.world.getBlockId(this.xTile, this.yTile, this.zTile) == Blocks.ROPE.id()) {
					this.x = (double)this.xTile + 0.5;
					this.y = (double)this.yTile + 0.5;
					this.z = (double)this.zTile + 0.5;
					return;
				}

				this.setInGround(false);
				this.xd *= (double)(this.random.nextFloat() * 0.2F);
				this.yd *= (double)(this.random.nextFloat() * 0.2F);
				this.zd *= (double)(this.random.nextFloat() * 0.2F);
				this.ticksInAir = 0;
				this.ticksCatchable = 0;
			}

			++this.ticksInAir;
			Vec3 currentPos = Vec3.getTempVec3(this.x, this.y, this.z);
			Vec3 nextPos = Vec3.getTempVec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			HitResult clip = this.world.checkBlockCollisionBetweenPoints(currentPos, nextPos);
			currentPos = Vec3.getTempVec3(this.x, this.y, this.z);
			nextPos = Vec3.getTempVec3(this.x + this.xd, this.y + this.yd, this.z + this.zd);
			if (clip != null) {
				nextPos = Vec3.getTempVec3(clip.location.x, clip.location.y, clip.location.z);
				if (clip.hitType == HitResult.HitType.TILE && this.world.getBlockId(clip.x, clip.y, clip.z) == Blocks.ROPE.id()) {
					this.setInGround(true);
					this.xTile = clip.x;
					this.yTile = clip.y;
					this.zTile = clip.z;
				}
			}

			Entity entity = null;
			List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.bb.expand(this.xd, this.yd, this.zd).grow(1.0, 1.0, 1.0));
			d3 = 0.0;
			Iterator var8 = list.iterator();

			while(true) {
				Entity e;
				double d7;
				do {
					HitResult newHitResult;
					do {
						do {
							do {
								if (!var8.hasNext()) {
									if (entity != null) {
										clip = new HitResult(entity);
									}

									if (clip != null && clip.entity != null && clip.entity.hurt(this.owner, 0, DamageType.COMBAT)) {
										this.hookedEntity = clip.entity;
									}

									this.move(this.xd, this.yd, this.zd);
									float f = MathHelper.sqrt(this.xd * this.xd + this.zd * this.zd);
									this.yRot = (float)(Math.atan2(this.xd, this.zd) * 180.0 / Math.PI);

									for(this.xRot = (float)(Math.atan2(this.yd, (double)f) * 180.0 / Math.PI); this.xRot - this.xRotO < -180.0F; this.xRotO -= 360.0F) {
									}

									while(this.xRot - this.xRotO >= 180.0F) {
										this.xRotO += 360.0F;
									}

									while(this.yRot - this.yRotO < -180.0F) {
										this.yRotO -= 360.0F;
									}

									while(this.yRot - this.yRotO >= 180.0F) {
										this.yRotO += 360.0F;
									}

									this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2F;
									this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2F;
									float movementScale = 0.92F;
									if (this.onGround || this.horizontalCollision) {
										movementScale = 0.5F;
									}

									int k = 5;
									double d5 = 0.0;

									int catchRate;
									for(catchRate = 0; catchRate < k; ++catchRate) {
										double d8 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)catchRate / (double)k - 0.125 + 0.125;
										double d9 = this.bb.minY + (this.bb.maxY - this.bb.minY) * (double)(catchRate + 1) / (double)k - 0.125 + 0.125;
										AABB axisalignedbb1 = AABB.getTemporaryBB(this.bb.minX, d8, this.bb.minZ, this.bb.maxX, d9, this.bb.maxZ);
										if (this.world.isAABBInMaterial(axisalignedbb1, Material.water)) {
											d5 += 1.0 / (double)k;
										}
									}

									if (d5 > 0.0) {
										if (this.ticksCatchable > 0) {
											--this.ticksCatchable;
										} else {
											catchRate = 500;
											int rainRate = 0;
											int algaeRate = 0;
											if (this.world.canBlockBeRainedOn(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z))) {
												rainRate = 200;
											}

											if (this.world.getBlockId(MathHelper.floor(this.x), MathHelper.floor(this.y) + 1, MathHelper.floor(this.z)) == Blocks.ALGAE.id()) {
												algaeRate = 100;
											}

											catchRate = catchRate - rainRate - algaeRate;
											if (this.random.nextInt(catchRate) == 0) {
												this.ticksCatchable = this.random.nextInt(30) + 10;
												this.yd -= 0.2;
												this.world.playSoundAtEntity((Entity)null, this, "random.splash", 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
												float f3 = (float)MathHelper.floor(this.bb.minY);

												int j1;
												double zOff;
												double xOff;
												for(j1 = 0; (float)j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (double)((this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth);
													zOff = (double)((this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth);
													this.world.spawnParticle("bubble", this.x + xOff, (double)(f3 + 1.0F), this.z + zOff, this.xd, this.yd - (double)(this.random.nextFloat() * 0.2F), this.zd, 0);
												}

												for(j1 = 0; (float)j1 < 1.0F + this.bbWidth * 20.0F; ++j1) {
													xOff = (double)((this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth);
													zOff = (double)((this.random.nextFloat() * 2.0F - 1.0F) * this.bbWidth);
													this.world.spawnParticle("splash", this.x + xOff, (double)(f3 + 1.0F), this.z + zOff, this.xd, this.yd, this.zd, 0);
												}
											}
										}
									}

									if (this.ticksCatchable > 0) {
										this.yd -= (double)(this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2;
									}

									d7 = d5 * 2.0 - 1.0;
									this.yd += 0.04 * d7;
									if (d5 > 0.0) {
										movementScale = (float)((double)movementScale * 0.9);
										this.yd *= 0.8;
									}

									this.xd *= (double)movementScale;
									this.yd *= (double)movementScale;
									this.zd *= (double)movementScale;
									this.setPos(this.x, this.y, this.z);
									return;
								}

								e = (Entity)var8.next();
							} while(!e.isPickable());
						} while(e == this.owner && this.ticksInAir < 5);

						float f2 = 0.3F;
						AABB aabb = e.bb.grow((double)f2, (double)f2, (double)f2);
						newHitResult = aabb.clip(currentPos, nextPos);
					} while(newHitResult == null);

					d7 = currentPos.distanceTo(newHitResult.location);
				} while(!(d7 < d3) && d3 != 0.0);

				entity = e;
				d3 = d7;
			}
		}
	}

	public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
	}

	public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
	}

	public float getShadowHeightOffs() {
		return 0.0F;
	}

	public int yoink() {
		int damage = 0;
		double dx;
		double dy;
		double dz;
		double distance;
		double scale;
		if (this.isInGround()) {
			dx = this.x - this.owner.x;
			dy = this.y - this.owner.y;
			dz = this.z - this.owner.z;
			distance = (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= distance;
			dy /= distance;
			dz /= distance;
			scale = 0.6;
			dx = MathHelper.clamp(dx, -scale, scale);
			dy = MathHelper.clamp(dy, -scale, scale);
			dz = MathHelper.clamp(dz, -scale, scale);
			scale = 2.0;
			Player var10000 = this.owner;
			var10000.xd += dx * scale;
			var10000 = this.owner;
			var10000.yd += dy * scale;
			var10000 = this.owner;
			var10000.zd += dz * scale;
			damage = 5;
		}

		if (this.hookedEntity != null) {
			dx = this.owner.x - this.x;
			dy = this.owner.y - this.y;
			dz = this.owner.z - this.z;
			distance = (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
			scale = 0.1;
			Entity var15 = this.hookedEntity;
			var15.xd += dx * scale;
			var15 = this.hookedEntity;
			var15.yd += dy * scale + (double) MathHelper.sqrt(distance) * 0.08;
			var15 = this.hookedEntity;
			var15.zd += dz * scale;
			damage = 3;
		} else if (this.ticksCatchable > 0) {
			EntityItem entityitem = new EntityItem(this.world, this.x, this.y, this.z, getFishByProbability().getDefaultStack());
			dx = this.owner.x - this.x;
			dy = this.owner.y - this.y;
			dz = this.owner.z - this.z;
			distance = (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
			scale = 0.1;
			entityitem.xd = dx * scale;
			entityitem.yd = dy * scale + (double) MathHelper.sqrt(distance) * 0.08;
			entityitem.zd = dz * scale;
			this.world.entityJoinedWorld(entityitem);
			this.owner.addStat(StatList.fishCaughtStat, 1);
			damage = 1;
		}
		return damage;
	}

	public static Item getFishByProbability() {
		int randomValue = (int) (Math.random() * 100); // Genera un número aleatorio entre 0 y 99

		if (randomValue < 6) { // Pufferfish: 6%
			return pufferfish;
		} else if (randomValue < 18) { // Salmon: 12% (6% + 12%)
			return Items.FOOD_FISH_RAW;
		} else if (randomValue < 24) { // Raw Fish: 6% (18% + 6%)
			return salmon;
		} else if (randomValue < 48) { // Tropical Fish: 24% (24% + 24%)
			return cod_fish;
		} else if (randomValue < 72) { // Cod: 24% (48% + 24%)
			return tropical_fish;
		} else if (randomValue < 82) { // Gold Fish: 10% (72% + 10%)
			return gold_fish;
		} else { // Cooked Gold Fish: 18% (82% + 18%)
			return Items.AMMO_PEBBLE;
		}
	}
}

