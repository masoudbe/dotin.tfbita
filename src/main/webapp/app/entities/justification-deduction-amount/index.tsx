import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import JustificationDeductionAmount from './justification-deduction-amount';
import JustificationDeductionAmountDetail from './justification-deduction-amount-detail';
import JustificationDeductionAmountUpdate from './justification-deduction-amount-update';
import JustificationDeductionAmountDeleteDialog from './justification-deduction-amount-delete-dialog';

const JustificationDeductionAmountRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<JustificationDeductionAmount />} />
    <Route path="new" element={<JustificationDeductionAmountUpdate />} />
    <Route path=":id">
      <Route index element={<JustificationDeductionAmountDetail />} />
      <Route path="edit" element={<JustificationDeductionAmountUpdate />} />
      <Route path="delete" element={<JustificationDeductionAmountDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JustificationDeductionAmountRoutes;
