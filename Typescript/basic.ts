// Class
class User {
    name!: string;
    age?: number;
    say() {
        console.log("hello " + this.name);
    }
}

let user = new User();
user.name = "wtl";
user.age = 30;
user.say();


// 泛型
class Person<IProps = {}> {
    user!: IProps;
    say() {
        console.log(this.user)
    }
}

interface IUser {
    name: string;
    age?: number;
}

let p = new Person<IUser>();
p.user = {name: "wtl2", age: 30}
p.say();


// 枚举
enum Direction {
    UP = 0,
    DOWN = 1,
    LEFT = 2,
    RIGHT = 3
}
enum Direction2 {
    UP = "up",
    DOWN = "down",
    LEFT = "left",
    RIGHT = "right"
}
console.log(Direction.LEFT);


// 多态
interface IPerson {
    name: string
}

class Tom implements IPerson {
    name!: string
    age!: number
}
class Jim implements IPerson {
    name!: string
}
class Teacher {
    person!: IPerson

    callPersonName() {
        console.log(this.person.name);
    }
}
let t = new Tom();
t.name = "Tom";
t.age = 10;
let j = new Jim()
j.name = "Jim";
let tech = new Teacher();
tech.person = t;
tech.callPersonName();
tech.person = j;
tech.callPersonName();