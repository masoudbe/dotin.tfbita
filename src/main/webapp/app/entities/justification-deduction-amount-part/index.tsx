import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import JustificationDeductionAmountPart from './justification-deduction-amount-part';
import JustificationDeductionAmountPartDetail from './justification-deduction-amount-part-detail';
import JustificationDeductionAmountPartUpdate from './justification-deduction-amount-part-update';
import JustificationDeductionAmountPartDeleteDialog from './justification-deduction-amount-part-delete-dialog';

const JustificationDeductionAmountPartRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<JustificationDeductionAmountPart />} />
    <Route path="new" element={<JustificationDeductionAmountPartUpdate />} />
    <Route path=":id">
      <Route index element={<JustificationDeductionAmountPartDetail />} />
      <Route path="edit" element={<JustificationDeductionAmountPartUpdate />} />
      <Route path="delete" element={<JustificationDeductionAmountPartDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JustificationDeductionAmountPartRoutes;
