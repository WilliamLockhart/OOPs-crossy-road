    package entity;
    import entity.Behavior.*;
    import sprite.*;

    public abstract class Entity {
        protected Sprite sprite;
        protected Behavior behavior;

        public abstract void update(double dt);
        public Sprite getSprite(){return sprite;}

        public void playSound(){behavior.playSound();}
        public void doAction(){behavior.doAction();}
    }
