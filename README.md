[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ZpkbBrmt)

# Notes
- ย้าย static value ของ broom ใส่ enum
- should i load map resource by RenderableHolder like others?
- packageต้องเปนตัวพิมพ์เล็กมั้ยนะ
# Add On later
- BaseCharacter/weatherEffected
- เราน่าจะต้องมาตกลงกันเรื่อง weatherEffected เพิ่มด้วยๆ
- GameController.getInstance().get() (almost done)
- we should add growthBar for each veggie
- implement efficient collideBox for every CollidableEntity

# Update 6/5/24
- เราย้ายตัว timer มาที่ timerThread ใน main
- ลองดูการคุมเคลื่อนไหวใน walk ของ player
- ถ้าสร้างตัวละครต้องใส่ใส่ Irenderable ไปด้วย + RenderableHolder.getInstance().add(this.player);
- ต้อง implement การ draw ของตัวละคร ดูใน player ได้
- set isDestroy = true ในตัวที่ตายแล้ว

[bug report]
- change clock.getTimer -< getCoolDown
- implement ตัวละครชนขอบซ้าย-ขวา

# Update 8/5/24
[บีม]
- เขียนดักขอบเกม [done]
- implement weather [done]
- ดักการสุ่มเกิดในบ้าน [done]

[นีร]
- ทำจอให้ใหญ่ขึ้น [done]
- HP bar & water bar [done]
- Z alignment [done]
  - player 999
  - broom 8**
  - slime 4**
  - veggie 6**
- ใส่ภาพสไลม์&พีช
- สไลม์ 3 สายพันธ์
  
# Update 9/5/24 (night)
- เรามีอัพเดท GUI เพิ่มเติมละ แต่คิดว่าจะเขียนใหม่อีกทีตอนที่วาดทุกอย่างเสร็จแล้ว
- ใน gamePanel มีฟังก์ชันสำหรับอัพเดทพวกveggieกับgameModeLabel ไปดูได้ๆ
- ตอนนี้เราวาดveggieเสร็จ2อัน เดี๋ยวอีกอันจะวาดตามมาให้ มีanimationที่น่าจะใช้ได้อยู่ ปล.เปลี่ยนชื่อได้นะ คิดไม่ออก5555
- เราเก็บจำนวนveggieที่ต้องเก็บในgameController แต่นีรแก้ได้เลยๆ

# Update 10/5/24
- เค้าโยกพวก Thread ของการเล่นไปใส่ใน gameController หมดเลยนะ
