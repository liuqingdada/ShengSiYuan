namespace java com.shengsiyuan.thrift
namespace py com.shengsiyuan.thrift.python

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {
    1: optional String name,
    2: optional int age,
    3: optional boolean married,
    4: optional list<String> children,
    5: optional map<int, String> mapper
}

exception DataException {
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

service PersonService {
    Person getPersonByName(1: required String name) throws (1: DataException de),

    void savePerson(1: required Person person) throws (1: DataException de)
}
