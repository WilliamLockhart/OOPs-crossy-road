    package entity;
    import entity.Behavior.*;
import entity.HitBox.HitBox;
import sprite.*;

    public abstract class Entity {
        protected Sprite sprite;
        protected Behavior behavior;
        protected HitBox hitBox;

        public abstract void update(double dt);

        //setters
        public void setHitBox(HitBox hitBox){
            this.hitBox = hitBox;
            updateHitbox();
        }
       
        public void setEntityPostion(double x, double y){
            sprite.setPostion(x, y);
            updateHitbox();
        }

        public void moveEntity(double x, double y){
            sprite.moveSprite(x, y); 
            updateHitbox();
        }
        //Getters
        public Sprite getSprite(){return sprite;}
        public HitBox getHitBox(){return hitBox;}

        public void playSound(){behavior.playSound();}
        public void doAction(){behavior.doAction();}


        //helper functions
        private void updateHitbox(){
            if(hitBox == null)
                return;

            double[] pos = sprite.getPosition();
            hitBox.setHitBoxPosition(pos[0], pos[1]);
        }
    }
