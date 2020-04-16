# ProfileSelector
this is the demo project for Shaadi.com - Android coding challenge

## Objective
You have to make a project which have a card which look alike Shaadi matches card with 2 action button in a recycle view and the same should be stored in database. On click of button(accept/decline) on card, the button should be replaced with message saying member declined/member accepted and the same should be updated in database. It should work in offline mode and the state should persist.

Use below link for API call
```
https://randomuser.me/api/?results=10
```
Please use the best in class libraries , design patterns to achieve above thing.

## Submission
The app can be accessed in two ways
- **Direct ( _Easy Way_ ) :**
you can directly download the apk file from the git repo. and install it on you test device.

- **Rebuild ( _Recommended_ ) :**
Clone the git repo and open it in Android Studio. Rebuild the project and deploy it on your test device. This method ensures upto-date code deployment with the latest commits.


## Task List
Development process is divided into minor task to keep track of the process

- [x] Accept / Reject profiles
- [x] Database Implemented / Updates
- [x] Persist State
- [x] Work Offline
- [ ] Paging DiffUtil Implementation
- [ ] Save 'intrest' preference
- [ ] Pull to Refresh

## Architecture components 
 Architecture components help to design robust, testable and maintainable apps.

- **Data Binding :**
Declaratively bind observable data to UI elements

- **Lifecycles :**
Manage your activity and fragment lifecycles

- **LiveData :**
Notify views when underlying database changes

- **Paging :**
Gradually load information on demand from your data source

- **Room :**
Fluent SQLite database access

- **ViewModel :**
Manage UI-related data in a lifecycle-conscious way
