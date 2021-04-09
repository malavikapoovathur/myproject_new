import random
from django.db.models import Q
from django.core.files.storage import FileSystemStorage
from django.shortcuts import render, redirect
from .models import *

# Create your views here.
def loginn(request):
    return render(request,"login.html")

def adm_home(request):
    return render(request,"ADMIN/admin_home.html")
def adm_add_batch(request):
    if request.method=="POST":
        batch_name=request.POST['batch']
        from_date=request.POST['from']
        to_date=request.POST['to']
        class_id=request.POST['class']
        class_obj=classmanage.objects.get(id=class_id)
        c1=batch(batchname=batch_name,fromdate=from_date,todate=to_date,CLASS=class_obj)
        c1.save()
        cc = classmanage.objects.all()
        return render(request, "ADMIN/add batch management.html",{'res':cc})
    else:
        cc=classmanage.objects.all()
        return render(request,"ADMIN/add batch management.html",{'res':cc})

def adm_add_class(request):
    if request.method=="POST":
        class_name=request.POST['txt_class']
        syll=request.POST['Select']
        cl=classmanage(classname=class_name,syllabus=syll)
        cl.save()
    return render(request,"ADMIN/ADD CLASS MANAGEMENT.html")

def adm_add_fee_structure(request):
    if request.method=="POST":
        fees=request.POST['fees']
        batch_id=request.POST['batch']
        class_id=request.POST['class']
        class_obj=classmanage.objects.get(id=class_id)
        batch_obj=batch.objects.get(id=batch_id)
        c1=feestructure(fees=fees,BATCH=batch_obj)
        c1.save()
    cc=batch.objects.all()
    return render(request,"ADMIN/add fee structure.html",{'res':cc})
def adm_add_monthly_fee(request):
    return render(request,"ADMIN/add monthly fee.html")
def adm_add_monthly_fees(request):
    return render(request,"ADMIN/add monthy fees.html")

def adm_add_student(request):
    if request.method == "POST":
        print("jjj")
        studentname = request.POST['name']
        place = request.POST['place']

        pin = request.POST['pin']
        district = request.POST['district']
        contactno = request.POST['contact']
        email = request.POST['email']
        gender = request.POST['gender']
        photo = request.FILES['photo']
        fs = FileSystemStorage()
        filename = fs.save(photo.name, photo)
        image = fs.url(filename)

        dob = request.POST['dob']
        parentname=request.POST['parentname']
        relationship=request.POST['relationship']
        parentphno=request.POST['parentphno']
        parentemailid=request.POST['parentemailid']
        batch_id = request.POST['batch']
        batch_obj = batch.objects.get(id=batch_id)
        password = str(random.randint(1000, 9999))
        log = login(username=email, password=password, type='student')
        log.save()
        res = student(LOGIN=log,studentname=studentname, place=place, pin=pin, district=district,contactno=contactno, email=email, gender=gender, photo=image, dob=dob,parentname=parentname,relationship=relationship,parentphno=parentphno,parentemailid=parentemailid,BATCH=batch_obj)
        res.save()
        return render(request,"ADMIN/add student management.html",)

    cc = batch.objects.all()
    return render(request, "ADMIN/add student management.html",{'res':cc})


def adm_add_subject(request):
    if request.method == "POST":
        subjectz= request.POST['subject']
        print(subjectz)
        batch_id = request.POST['batch']
        batch_obj = batch.objects.get(id=batch_id)
        print(batch_obj.id)
        c1 = subject(subject=subjectz, BATCH=batch_obj)
        print("ii")
        c1.save()
    cc = batch.objects.all()
    return render(request,"ADMIN/add subject management.html",{'res':cc})
def adm_add_timetable(request):
    return render(request,"ADMIN/add timetable.html")
def adm_add_tutor(request):
    if request.method=='POST':
        print("jjj")
        tutoname = request.POST['nam']
        placee = request.POST['place']
        postt = request.POST['post']
        pinn = request.POST['pin']
        districtt = request.POST['district']
        contact_number = request.POST['contact']
        emaill = request.POST['email']
        genderr = request.POST['gender']
        qualificationn = request.POST.getlist('qualification')
        li=','.join(qualificationn)
        photo = request.FILES['photo']
        fs=FileSystemStorage()
        filename=fs.save(photo.name,photo)
        image=fs.url(filename)

        dobb = request.POST['dob']
        experiencee = request.POST['experience']

        password=str(random.randint(1000,9999))
        log=login(username=emaill,password=password,type='tutor')
        log.save()
        res=tutor(LOGIN=log,tutorname=tutoname,place=placee,post=postt,pin=pinn,district=districtt,contactno=contact_number,email=emaill,gender=genderr,qualification=li,photo=image,dob=dobb,experience=experiencee)
        res.save()
    return render(request,"ADMIN/add tutor management.html")

def adm_add_tutors_subject(request):
    res = tutor.objects.all()
    res1 = batch.objects.all()
    res2 = subject.objects.all()
    return render(request, "ADMIN/add tutor subject.html", {'res': res, 'res1': res1, 'res2': res2})
def adm_add_tutors_attendence(request):
    if request.method=="POST":
      date=request.POST['datez']
      class_hour=request.POST['hourz']
      tutor_id=request.POST['tutor']
      # batch_id=request.POST['batch']
      subject_id=request.POST['subject']
      tutor_obj=tutor.objects.get(id=tutor_id)
      # batch_obj=batch.objects.get(id=batch_id)
      subject_obj=subject.objects.get(id=subject_id)
      c1=staffattendence(date=date,classhour=class_hour,SUBJECT=subject_obj,TUTOR=tutor_obj)
      c1.save()
    res = tutor.objects.all()
    # res1 = batch.objects.all()
    res2 = subject.objects.all()
    return render(request,"ADMIN/add tutors attendence.html",{'res':res,'res2':res2})
def adm_add_tutors_subject_post(request):
    print("jjjjj")
    tutor_id=request.POST["tutor"]
    print(tutor)
    batch_id=request.POST['batch']
    print(batch_id)
    subject_id=request.POST['subject']
    print(subject_id)
    tutor_obj=tutor.objects.get(pk=tutor_id)
    batch_obj=batch.objects.get(pk=batch_id)
    subject_obj=subject.objects.get(pk=subject_id)
    c1=subjectallocation(SUBJECT=subject_obj,TUTOR=tutor_obj)
    c1.save()
    res = tutor.objects.all()
    res1 = batch.objects.all()
    res2 = subject.objects.all()
    return render(request, "ADMIN/add tutor subject.html",{'res': res, 'res1': res1, 'res2': res2})

def adm_add_tutors_salary(request):
    return render(request,"ADMIN/Add tutors salary.html")
def adm_add_vacancy(request):
    if request.method == "POST":
        totalvacancy = request.POST['vaccancy']
        otherinfo = request.POST['textarea']
        print(totalvacancy)
        subject_id = request.POST['subject']
        subject_obj = subject.objects.get(id=subject_id)
        print(subject_obj.id)
        c1 = vaccancy(totalnoofvaccancy=totalvacancy,otherinfo=otherinfo,SUBJECT=subject_obj)
        print("ii")
        c1.save()
    cc = subject.objects.all()
    return render(request,"ADMIN/Add vacancy.html",{'res':cc})
def adm_due_report(request):
    return render(request,"ADMIN/due report.html")
def adm_give_reply(request):
    return render(request,"ADMIN/give reply.html")
def adm_update_batch(request,pk):
    request.session['id']=pk
    batch_obj = batch.objects.get(id=pk)
    res=classmanage.objects.all()
    return render(request,"ADMIN/update batch management.html",{'res':res, 'data':batch_obj})

def adm_update_batch_post(request):
    id=request.session['id']
    if request.method == "POST":
        batch_name = request.POST['batch']
        from_date = request.POST['from']
        to_date = request.POST['to']
        class_id = request.POST['class']
        res=batch.objects.get(id=id)
        res.batchname=batch_name
        res.fromdate=from_date
        res.todate=to_date
        res.CLASS_id=class_id
        res.save()
    return redirect("/myapp/adm_view_batch/")
def adm_update_class(request,pk):
    request.session['id']=pk
    classmanage_obj=classmanage.objects.get(id=pk)
    res=classmanage.objects.all()
    return render(request,"ADMIN/UPDATE CLASS MANAGEMENT.html",{'res':res,'data':classmanage_obj})
def adm_update_class_post(request):
    id=request.session['id']
    if request.method=="POST":
        class_name=request.POST['class']
        syllabus=request.POST['syllabus']
        res=classmanage.objects.get(id=id)
        res.classname=class_name
        res.syllabus=syllabus
        res.save()
        return redirect("/myapp/adm_view_class/")

def adm_update_fee_structure(request,pk):
    request.session['id']=pk
    feestructure_obj=feestructure.objects.get(id=pk)
    res=feestructure.objects.all()
    return render(request,"ADMIN/update fee structure.html",{'res':res,'data':feestructure_obj})
def adm_update_fee_structure_post(request):
    id=request.session['id']
    if request.method=="POST":
        fees=request.POST['fees']
        res=feestructure.objects.get(id=id)
        res.fees=fees
        res.save()
        return redirect("/myapp/adm_view_fee_structure/")
def adm_update_student(request):
    return render(request,"ADMIN/update student management.html")
def adm_update_subject(request,pk):
    request.session['id']=pk
    subject_obj=subject.objects.get(id=pk)
    res=subject.objects.all()
    return render(request,"ADMIN/UPDATE SUBJECT MANAGEMENT.html",{'res':res,'data':subject_obj})
def adm_update_subject_post(request):
    id=request.session['id']
    if request.method=="POST":
        subjects=request.POST['subjectz']
        res=subject.objects.get(id=id)
        res.subject=subjects
        res.save()
        return redirect("/myapp/adm_view_subject/")
def adm_update_timetable(request):
    return render(request,"ADMIN/ update timetable.html")
def adm_update_tutor(request,pk):
    request.session['id']=pk
    tutor_obj=tutor.objects.get(id=pk)
    res=tutor.objects.all()
    qul = tutor_obj.qualification.split(',')
    print(qul)
    return render(request,"ADMIN/update tutor management.html",{'res':res,'data':tutor_obj,'degree':qul})
def adm_update_tutor_post(request):
    id=request.session['id']
    print(id)
    if request.method=="POST":
        tutor_name=request.POST['name']
        print(tutor_name)
        place=request.POST['place']
        print(place)
        post=request.POST['post']
        print(post)
        district=request.POST['district']
        print(district)
        contactno=request.POST['contact']
        print(contactno)
        email=request.POST['email']
        print(email)
        qualificationn = request.POST.getlist('qualification')
        print(qualificationn)
        li = ','.join(qualificationn)

        res=tutor.objects.get(pk=id)

        res.tutorname=tutor_name
        res.place=place
        res.post=post
        res.district=district
        res.contactno=contactno
        res.email=email
        res.qualification=li
        res.save()
        return redirect("/myapp/adm_view_tutor/")
def adm_update_tutors_subject(request):
    return render(request,"ADMIN/update tutor subject.html")
def adm_search_tutors_post(request):
    search=request.POST['txt1']
    res=tutor.objects.filter(tutorname__contains=search)
    return render(request, "ADMIN/view tutor management.html", {'res': res})


def adm_update_vacancy(request,pk):
    request.session['id']=pk
    vaccancy_obj=vaccancy.objects.get(id=pk)
    res=subject.objects.all()
    return render(request,"ADMIN/update vacancy.html",{'res':res,'data':vaccancy_obj})
def adm_update_vacancy_post(request):
    id=request.session['id']
    if request.method=="POST":
        totalvacancy = request.POST['vaccancy']
        otherinfo = request.POST['otherinfo']

        res=vaccancy.objects.get(id=id)
        res.totalnoofvaccancy=totalvacancy
        res.otherinfo=otherinfo

        res.save()
        return redirect("/myapp/adm_view_vacancy/")
def adm_update_tutors_attendence(request,pk):
    staffattendence_obj=staffattendence.objects.get(id=pk)
    print(staffattendence)
    request.session['id'] = pk
    res=tutor.objects.all()
    # res1=batch.objects.all()
    res2=subject.objects.all()
    return render(request,"ADMIN/update tutors attendence.html",{'res':res,'res2':res2,'data':staffattendence_obj})
def adm_update_tutors_attendence_post(request):
    id=request.session['id']
    if request.method=="POST":
        date=request.POST['datez']
        class_hour=request.POST['hourz']
        tutor_id=request.POST['tutor']
        subject_id=request.POST['subject']
        tutor_obj=tutor.objects.get(id=tutor_id)
        subject_obj=subject.objects.get(id=subject_id)
        res=staffattendence.objects.get(id=id)
        res.date=date
        res.classhour=class_hour
        res.TUTOR=tutor_obj
        res.SUBJECT=subject_obj
        res.save()
        # batch=request.POST['batch']
        return redirect("/myapp/adm_view_attendence_of_tutors/")
def adm_view_attendence_of_tutors(request):
    res=staffattendence.objects.all()
    return render(request,"ADMIN/view attendence of tutors.html",{'res':res})
def adm_search_attendence_of_tutors_post(request):
     if request.method=="POST":
         from_date=request.POST['fromdate']
         to_date=request.POST['todate']
         res=staffattendence.objects.filter(date__range=(from_date, to_date))
         return render(request, "ADMIN/view attendence of tutors.html", {'res': res})


def adm_view_attendence_of_tutors_del(request,pk):
    res=staffattendence.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_attendence_of_tutors/")
def adm_view_batch(request):
    res=batch.objects.all()
    return render(request,"ADMIN/view batch management.html",{'res':res})
def adm_view_batch_del(request,pk):
    res=batch.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_batch/")
def adm_view_class(request):
    res=classmanage.objects.all()
    return render(request,"ADMIN/View class management.html",{'res':res})
def adm_view_class_post(request):
    classname=request.POST['txt_class']
    res=classmanage.objects.filter(classname=classname)
    return render(request,"ADMIN/View class management.html",{'res':res})
def adm_view_class_del(request,pk):
    res=classmanage.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_class/")
def adm_view_complaint(request):
    return render(request,"ADMIN/View complaint and give reply.html")
def adm_view_fee_structure(request):
    res1=batch.objects.all()
    res2=classmanage.objects.all()
    res3 = feestructure.objects.all()
    print(res1)
    print(res2)
    print(res3)
    return render(request,"ADMIN/view fee structure.html",{'res3':res3,'res1':res1,'res2':res2})
def adm_view_fee_structure_del(request,pk):
    res=feestructure.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_fee_structure/")

def adm_search_fee_structure_post(request):
    print("jjj222")
    # csearch=request.POST['class']
    bsearch = request.POST['sss']
    print("hai")
    print(bsearch)
    bb=batch.objects.get(id=bsearch)
    print("ooo",bb)
    res=feestructure.objects.get(BATCH=id)
    print("kkk")
    print(res)

    return render(request, "ADMIN/view fee structure.html", {'res': res})
def adm_view_feedback(request):
    return render(request,"ADMIN/view feedback.html")
def adm_view_leave_request(request):
    return render(request,"ADMIN/View leave request.html")
def adm_view_monthly_fee_reports(request):
    return render(request,"ADMIN/view monthly fee reports.html")
def adm_view_monthly_salary_reports(request):
    return render(request,"ADMIN/view monthly salary report.html")
def adm_view_student(request):
    res=student.objects.all()
    return render(request,"ADMIN/view student management.html",{'res':res})

def adm_view_subject(request):
    res=subject.objects.all()
    return render(request,"ADMIN/VIEW SUBJECT MANAGEMENT.html",{'res':res})
def adm_view_subject_del(request,pk):
    res=subject.objects.get(id=pk)
    res.delete()
    return render(request,"ADMIN/VIEW SUBJECT MANAGEMENT.html")
def adm_view_timetable(request):
    return render(request,"ADMIN/view timetable.html")
def adm_view_tutor(request):
    res=tutor.objects.all()
    return render(request,"ADMIN/view tutor management.html",{'res':res})
def adm_view_tutor_del(request,pk):
    res=tutor.objects.get(id=pk)
    res.delete()
    return render(request,"ADMIN/admin_home.html")
def adm_view_tutors_subject(request):
    res=subjectallocation.objects.all()
    return render(request,"ADMIN/view tutor subject.html",{'res':res})
def adm_view_tutors_subject_del(request,pk):
    res=subjectallocation.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_tutors_subject/")
def adm_view_vacancy(request):
    res=vaccancy.objects.all()
    return render(request,"ADMIN/view vaccancy.html",{'res':res})
def adm_view_vacancy_del(request,pk):
    res=vaccancy.objects.get(id=pk)
    res.delete()
    return redirect("/myapp/adm_view_vacancy/")
def adm_view_yearly_salary_reports(request):
    return render(request,"ADMIN/view yearly salary reports.html")

def ab(request):
    return render(request,"admin_index.html")