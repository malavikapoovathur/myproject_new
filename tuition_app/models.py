from django.db import models

# Create your models here.
class login(models.Model):
    username=models.CharField(max_length=50)
    password=models.CharField(max_length=50)
    type=models.CharField(max_length=50)
    class Meta:
        db_table='login'

class tutor(models.Model):
    tutorname=models.CharField(max_length=50)
    place=models.CharField(max_length=50)
    pin=models.CharField(max_length=50)
    post=models.CharField(max_length=50)
    district=models.CharField(max_length=50)
    contactno=models.CharField(max_length=50)
    email=models.CharField(max_length=50)
    photo=models.CharField(max_length=200)
    qualification=models.CharField(max_length=50)
    gender=models.CharField(max_length=50)
    dob=models.CharField(max_length=50)
    experience=models.CharField(max_length=50)
    LOGIN=models.ForeignKey(login,on_delete=models.CASCADE)
    hourlypayment=models.CharField(max_length=50)

    class Meta:
        db_table='tutor'


class classmanage(models.Model):
    classname=models.CharField(max_length=50)
    syllabus=models.CharField(max_length=50)
    class Meta:
        db_table='classmanage'

class batch(models.Model):
    batchname = models.CharField(max_length=50)
    fromdate = models.DateField(max_length=50)
    todate = models.DateField(max_length=50)
    CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    strength=models.CharField(max_length=50)
    class Meta:
        db_table = 'batch'

class student(models.Model):
    studentname=models.CharField(max_length=50)
    place = models.CharField(max_length=50)
    pin = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    contactno = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    photo = models.CharField(max_length=200)
    gender = models.CharField(max_length=50)
    dob = models.CharField(max_length=50)
    parentname=models.CharField(max_length=50)
    relationship=models.CharField(max_length=50)
    parentphno=models.CharField(max_length=50)
    parentemailid=models.CharField(max_length=50)
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE)
    # CLASS=models.ForeignKey(classmanage,on_delete=models.CASCADE)
    BATCH=models.ForeignKey(batch,on_delete=models.CASCADE)
    class Meta:
        db_table='student'

class subject(models.Model):
    subject=models.CharField(max_length=50)
    # CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    BATCH=models.ForeignKey(batch,on_delete=models.CASCADE)
    class Meta:
        db_table='subject'

class leave(models.Model):
    reasonforleave=models.CharField(max_length=50)
    status=models.CharField(max_length=50)
    TUTOR=models.ForeignKey(tutor,on_delete=models.CASCADE)
    date=models.CharField(max_length=50)
    class Meta:
        db_table='leave'

class vaccancy(models.Model):
    totalnoofvaccancy=models.CharField(max_length=50)
    otherinfo=models.CharField(max_length=50)
   # CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    SUBJECT=models.ForeignKey(subject,on_delete=models.CASCADE)
    class Meta:
        db_table='vaccancy'






class timetable(models.Model):
    day=models.CharField(max_length=50)
    slot=models.CharField(max_length=50)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    #BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    TUTOR=models.ForeignKey(tutor,on_delete=models.CASCADE)
    class Meta:
        db_table='timetable'

class notification(models.Model):
    date=models.CharField(max_length=50)
    notification=models.CharField(max_length=50)
   # CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    class Meta:
        db_table='notification'

class testdetails(models.Model):
    testname=models.CharField(max_length=50)
    date=models.DateField(max_length=50)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
   # BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    class Meta:
        db_table='testdetails'

class testresult(models.Model):
    mark=models.CharField(max_length=50)
    status=models.CharField(max_length=50)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
   # BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
   # SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    STUDENT=models.ForeignKey(student,on_delete=models.CASCADE)
    TEST=models.ForeignKey(testdetails,on_delete=models.CASCADE)
    class Meta:
        db_table='testresult'

class attendenceofstudents(models.Model):
    status=models.CharField(max_length=50)
   # CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
   # BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    date=models.CharField(max_length=50)
    timeslot=models.CharField(max_length=50)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    class Meta:
        db_table='attendenceofstudents'
class feestructure(models.Model):
    fees=models.CharField(max_length=50)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    class Meta:
        db_table='feestructure'
class feereports(models.Model):
    FEESTRUCTURE=models.ForeignKey(feestructure,on_delete=models.CASCADE)
    feespaid=models.CharField(max_length=50)
    status=models.CharField(max_length=50)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    #BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    class Meta:
        db_table='feereports'

class staffattendence(models.Model):
    classhour=models.CharField(max_length=50)
    TUTOR = models.ForeignKey(tutor, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    date = models.CharField(max_length=50)
    class Meta:
        db_table='staffattendence'

class staffsalary(models.Model):
    salary=models.CharField(max_length=50)
    TUTOR = models.ForeignKey(tutor, on_delete=models.CASCADE)
    date = models.CharField(max_length=50)
    class Meta:
        db_table='staffsalary'

class subjectallocation(models.Model):
    # CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    # BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    TUTOR = models.ForeignKey(tutor, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    class Meta:
        db_table='subjectallocation'

class studymaterial(models.Model):
    studymaterial=models.CharField(max_length=50)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    #BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    class Meta:
        db_table='studymaterial'
class parentmessage(models.Model):
    message=models.CharField(max_length=300)
    #CLASS = models.ForeignKey(classmanage, on_delete=models.CASCADE)
    #BATCH = models.ForeignKey(batch, on_delete=models.CASCADE)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE)
    class Meta:
        db_table='parentmessage'
class complaint(models.Model):
    date=models.CharField(max_length=50)
    complaint=models.CharField(max_length=90)
    status=models.CharField(max_length=50)
    reply=models.CharField(max_length=90)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    class Meta:
        db_table='complaint'
class feedback(models.Model):
    date=models.CharField(max_length=50)
    feedback=models.CharField(max_length=100)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE)
    class Meta:
        db_table='feedback'

class chat(models.Model):
    fromid=models.ForeignKey(student,on_delete=models.CASCADE)
    toid=models.ForeignKey(tutor,on_delete=models.CASCADE)
    date=models.CharField(max_length=50)
    message=models.CharField(max_length=300)
    time=models.CharField(max_length=50)
    type=models.CharField(max_length=50)
    class Meta:
        db_table='chat'
class subjectallocate(models.Model):
    TUTOR = models.ForeignKey(tutor, on_delete=models.CASCADE)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE)
    class Meta:
        db_table = 'subjectallocate'