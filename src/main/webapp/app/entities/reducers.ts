import region from 'app/entities/region/region.reducer';
import country from 'app/entities/country/country.reducer';
import location from 'app/entities/location/location.reducer';
import department from 'app/entities/department/department.reducer';
import task from 'app/entities/task/task.reducer';
import employee from 'app/entities/employee/employee.reducer';
import job from 'app/entities/job/job.reducer';
import jobHistory from 'app/entities/job-history/job-history.reducer';
import orderRegistrationInfo from 'app/entities/order-registration-info/order-registration-info.reducer';
import categoryElement from 'app/entities/category-element/category-element.reducer';
import custom from 'app/entities/custom/custom.reducer';
import licenceInfo from 'app/entities/licence-info/licence-info.reducer';
import orderRegServ from 'app/entities/order-reg-serv/order-reg-serv.reducer';
import product from 'app/entities/product/product.reducer';
import purchaseFromOtherResources from 'app/entities/purchase-from-other-resources/purchase-from-other-resources.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  region,
  country,
  location,
  department,
  task,
  employee,
  job,
  jobHistory,
  orderRegistrationInfo,
  categoryElement,
  custom,
  licenceInfo,
  orderRegServ,
  product,
  purchaseFromOtherResources,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
