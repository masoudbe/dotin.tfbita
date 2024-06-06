import orderRegistrationInfo from 'app/entities/order-registration-info/order-registration-info.reducer';
import categoryElement from 'app/entities/category-element/category-element.reducer';
import custom from 'app/entities/custom/custom.reducer';
import orderRegServ from 'app/entities/order-reg-serv/order-reg-serv.reducer';
import product from 'app/entities/product/product.reducer';
import licenceInfo from 'app/entities/licence-info/licence-info.reducer';
import purchaseFromOtherResources from 'app/entities/purchase-from-other-resources/purchase-from-other-resources.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  orderRegistrationInfo,
  categoryElement,
  custom,
  orderRegServ,
  product,
  licenceInfo,
  purchaseFromOtherResources,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
