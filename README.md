[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/ZpkbBrmt)

# Notes
- ย้าย static value ของ stick ใส่ enum
# Add On later
- BaseCharacter/weatherEffected
- เราน่าจะต้องมาตกลงกันเรื่อง weatherEffected เพิ่มด้วยๆ
- GameController.getInstance().get() (almost done)

# Update 6/5/24
- เราย้ายตัว timer มาที่ timerThread ใน main
- ลองดูการคุมเคลื่อนไหวใน walk ของ player
- ถ้าสร้างตัวละครต้องใส่ใส่ Irenderable ไปด้วย + RenderableHolder.getInstance().add(this.player);
- ต้อง implement การ draw ของตัวละคร ดูใน player ได้
- set isDestroy = true ในตัวที่ตายแล้ว

[bug report]
- change clock.getTimer -< getCoolDown
- implement ตัวละครชนขอบซ้าย-ขวา