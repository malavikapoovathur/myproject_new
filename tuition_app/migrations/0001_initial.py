# Generated by Django 2.0 on 2021-04-08 05:11

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='attendenceofstudents',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('status', models.CharField(max_length=50)),
                ('date', models.CharField(max_length=50)),
                ('timeslot', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'attendenceofstudents',
            },
        ),
        migrations.CreateModel(
            name='batch',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('batchname', models.CharField(max_length=50)),
                ('fromdate', models.DateField(max_length=50)),
                ('todate', models.DateField(max_length=50)),
                ('strength', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'batch',
            },
        ),
        migrations.CreateModel(
            name='chat',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=50)),
                ('message', models.CharField(max_length=300)),
                ('time', models.CharField(max_length=50)),
                ('type', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'chat',
            },
        ),
        migrations.CreateModel(
            name='classmanage',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('classname', models.CharField(max_length=50)),
                ('syllabus', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'classmanage',
            },
        ),
        migrations.CreateModel(
            name='complaint',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=50)),
                ('complaint', models.CharField(max_length=90)),
                ('status', models.CharField(max_length=50)),
                ('reply', models.CharField(max_length=90)),
            ],
            options={
                'db_table': 'complaint',
            },
        ),
        migrations.CreateModel(
            name='feedback',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=50)),
                ('feedback', models.CharField(max_length=100)),
            ],
            options={
                'db_table': 'feedback',
            },
        ),
        migrations.CreateModel(
            name='feereports',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('feespaid', models.CharField(max_length=50)),
                ('status', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'feereports',
            },
        ),
        migrations.CreateModel(
            name='feestructure',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('fees', models.CharField(max_length=50)),
                ('BATCH', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.batch')),
            ],
            options={
                'db_table': 'feestructure',
            },
        ),
        migrations.CreateModel(
            name='leave',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('reasonforleave', models.CharField(max_length=50)),
                ('status', models.CharField(max_length=50)),
                ('date', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'leave',
            },
        ),
        migrations.CreateModel(
            name='login',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('username', models.CharField(max_length=50)),
                ('password', models.CharField(max_length=50)),
                ('type', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'login',
            },
        ),
        migrations.CreateModel(
            name='notification',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=50)),
                ('notification', models.CharField(max_length=50)),
                ('BATCH', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.batch')),
            ],
            options={
                'db_table': 'notification',
            },
        ),
        migrations.CreateModel(
            name='parentmessage',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('message', models.CharField(max_length=300)),
                ('LOGIN', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.login')),
            ],
            options={
                'db_table': 'parentmessage',
            },
        ),
        migrations.CreateModel(
            name='staffattendence',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('classhour', models.CharField(max_length=50)),
                ('date', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'staffattendence',
            },
        ),
        migrations.CreateModel(
            name='staffsalary',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('salary', models.CharField(max_length=50)),
                ('date', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'staffsalary',
            },
        ),
        migrations.CreateModel(
            name='student',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('studentname', models.CharField(max_length=50)),
                ('place', models.CharField(max_length=50)),
                ('pin', models.CharField(max_length=50)),
                ('district', models.CharField(max_length=50)),
                ('contactno', models.CharField(max_length=50)),
                ('email', models.CharField(max_length=50)),
                ('photo', models.CharField(max_length=200)),
                ('gender', models.CharField(max_length=50)),
                ('dob', models.CharField(max_length=50)),
                ('parentname', models.CharField(max_length=50)),
                ('relationship', models.CharField(max_length=50)),
                ('parentphno', models.CharField(max_length=50)),
                ('parentemailid', models.CharField(max_length=50)),
                ('BATCH', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.batch')),
                ('LOGIN', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.login')),
            ],
            options={
                'db_table': 'student',
            },
        ),
        migrations.CreateModel(
            name='studymaterial',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('studymaterial', models.CharField(max_length=50)),
            ],
            options={
                'db_table': 'studymaterial',
            },
        ),
        migrations.CreateModel(
            name='subject',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('subject', models.CharField(max_length=50)),
                ('BATCH', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.batch')),
            ],
            options={
                'db_table': 'subject',
            },
        ),
        migrations.CreateModel(
            name='subjectallocate',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('SUBJECT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject')),
            ],
            options={
                'db_table': 'subjectallocate',
            },
        ),
        migrations.CreateModel(
            name='subjectallocation',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('SUBJECT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject')),
            ],
            options={
                'db_table': 'subjectallocation',
            },
        ),
        migrations.CreateModel(
            name='testdetails',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('testname', models.CharField(max_length=50)),
                ('date', models.DateField(max_length=50)),
                ('SUBJECT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject')),
            ],
            options={
                'db_table': 'testdetails',
            },
        ),
        migrations.CreateModel(
            name='testresult',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('mark', models.CharField(max_length=50)),
                ('status', models.CharField(max_length=50)),
                ('STUDENT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student')),
                ('TEST', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.testdetails')),
            ],
            options={
                'db_table': 'testresult',
            },
        ),
        migrations.CreateModel(
            name='timetable',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('day', models.CharField(max_length=50)),
                ('slot', models.CharField(max_length=50)),
                ('SUBJECT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject')),
            ],
            options={
                'db_table': 'timetable',
            },
        ),
        migrations.CreateModel(
            name='tutor',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tutorname', models.CharField(max_length=50)),
                ('place', models.CharField(max_length=50)),
                ('pin', models.CharField(max_length=50)),
                ('post', models.CharField(max_length=50)),
                ('district', models.CharField(max_length=50)),
                ('contactno', models.CharField(max_length=50)),
                ('email', models.CharField(max_length=50)),
                ('photo', models.CharField(max_length=200)),
                ('qualification', models.CharField(max_length=50)),
                ('gender', models.CharField(max_length=50)),
                ('dob', models.CharField(max_length=50)),
                ('experience', models.CharField(max_length=50)),
                ('hourlypayment', models.CharField(max_length=50)),
                ('LOGIN', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.login')),
            ],
            options={
                'db_table': 'tutor',
            },
        ),
        migrations.CreateModel(
            name='vaccancy',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('totalnoofvaccancy', models.CharField(max_length=50)),
                ('otherinfo', models.CharField(max_length=50)),
                ('SUBJECT', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject')),
            ],
            options={
                'db_table': 'vaccancy',
            },
        ),
        migrations.AddField(
            model_name='timetable',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='subjectallocation',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='subjectallocate',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='studymaterial',
            name='SUBJECT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject'),
        ),
        migrations.AddField(
            model_name='staffsalary',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='staffattendence',
            name='SUBJECT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.subject'),
        ),
        migrations.AddField(
            model_name='staffattendence',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='parentmessage',
            name='STUDENT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
        migrations.AddField(
            model_name='leave',
            name='TUTOR',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='feereports',
            name='FEESTRUCTURE',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.feestructure'),
        ),
        migrations.AddField(
            model_name='feereports',
            name='STUDENT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
        migrations.AddField(
            model_name='feedback',
            name='STUDENT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
        migrations.AddField(
            model_name='complaint',
            name='STUDENT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
        migrations.AddField(
            model_name='chat',
            name='fromid',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
        migrations.AddField(
            model_name='chat',
            name='toid',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.tutor'),
        ),
        migrations.AddField(
            model_name='batch',
            name='CLASS',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.classmanage'),
        ),
        migrations.AddField(
            model_name='attendenceofstudents',
            name='STUDENT',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tuition_app.student'),
        ),
    ]
